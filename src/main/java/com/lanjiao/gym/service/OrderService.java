package com.lanjiao.gym.service;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.common.ResponseService;
import com.lanjiao.gym.dao.*;
import com.lanjiao.gym.entity.*;
import com.lanjiao.gym.util.UniqueOrderGenerate;
import com.lanjiao.gym.wechat.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
public class OrderService extends ResponseService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderDetailDao orderDetailDao;

    @Autowired
    UniqueOrderGenerate uniqueOrderGenerate;

    @Autowired
    SubsiteDao subsiteDao;

    @Autowired
    TimeDao timeDao;

    @Autowired
    SportsDao sportsDao;

    @Autowired
    RedisUtil redisUtil;

    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Response insertOrder(Order order){

        //生成订单id
        String  orderId = String.valueOf(uniqueOrderGenerate.nextId());
        order.setOrderId(orderId);
//        order_status   1.待付款 2.已取消 3.未支付(过期）4.已付款
//        order_check 1.审核通过 ，2，审核未通过
//      0 不免费  1免费

        order.setOrderCheck(2);
        order.setOrderFree(0);
        order.setOrderStatus(1);
        System.out.println(order);
        int flag = orderDao.insertOrder(order);

        //生成详细订单信息
        for(OrderDetail orderDetail : order.getOrderDetailList()){
            orderDetail.setOrderId(orderId);
            //通过订购时间，子场地id,时间段查询场地是否被订购

            OrderDetail temOd = new OrderDetail();
            temOd.setSubsiteId(orderDetail.getSubsiteId());
            temOd.setDetailTime(orderDetail.getDetailTime());
            temOd.setTimeId(orderDetail.getTimeId());
            List<OrderDetail> orderDetailList =  orderDetailDao.queryOrderDetail(temOd);
           //如果子订单已订购，返回异常
           if(orderDetailList.size() != 0){

               try{
                   new Exception();
               }
               catch (Exception e){
                   e.printStackTrace();
               }
               return fail(60000,"该场地已被订购");
           }
            orderDetailDao.insertOrderDetail(orderDetail);

        }
        //将订单号存入redis中，设置过期时间是15分钟
        //order.getOrderDate();
        String key = "order:"+order.getOrderId();

        redisUtil.set(key,"",5*60);

        return success(order.getOrderId(),"订单生成成功");
    }


    //通过orderId查询订单
    public Response queryOrderByOrderId(@Param("orderId") String orderId, WxUser wxUser){

        Order order = new Order();
        order.setOrderId(orderId);
        //查询订单
        List<Order> orderList = orderDao.queryOrderByOrderId(order);

        //通过订单号查询
        OrderDetail tem = new OrderDetail();
        tem.setOrderId(orderId);
        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailDao.queryOrderDetail(tem);
        if(orderList !=null) {
            //将用户信息添加订单中
            orderList.get(0).setWxUser(wxUser);
        }

        for(OrderDetail orderDetail :orderDetailList){

            //根据子场地号，查询子场地详情
            List<Subsite> subsiteList = subsiteDao.querySubsiteBySubsiteId(orderDetail.getSubsiteId());
            Subsite subsite = subsiteList.get(0);
            //根据子场地id,查出运动类型
            //List<Sport> sportList = sportsDao.querySportBySportId(subsite.getSportId());

            //subsite.setSportName(sportList.get(0).getSportName());
            //根据timeId查询预约时间段
            List<Time> timeList = timeDao.queryTimeByTimeId(orderDetail.getTimeId());
            //设置订单详细属性
            orderDetail.setSubsite(subsite);
            orderDetail.setTime(timeList.get(0));
        }

        orderList.get(0).setOrderDetailList(orderDetailList);

        return success(orderList,"订单详情");
    }


    //通过订单状态查询
    public Response queryOrderByOrderStatus(List<Integer> listStatus,WxUser wxUser){

        Order order = new Order();
        order.setListStatus(listStatus);
        order.setUserId(wxUser.getOpenid());
        List<Order> orderList = orderDao.queryOrder(order);
        return success(orderList,wxUser.getNickname()+"状态"+listStatus+"的订单");
    }

    //查询用户的订单
    public Response queryOrder(WxUser wxUser){
        Order order = new Order();
        order.setUserId(wxUser.getOpenid());

        List<Order> orderList = orderDao.queryOrder(order);

        return success(orderList,wxUser.getNickname()+"的所有订单");
    }

    //取消订单状态
    @Transactional
    public Response cancelOrder(Order order){
        //order_status   1.待付款 2.已取消 3.未支付(过期）4.已付款
        // order_check 1.审核通过 ，2，审核未通过
        // order_free  0. 不免费 , 1.免费
        order.setOrderCheck(2);
        orderDao.updateOrder(order);
        //更新 order_detail中的状态
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(order.getOrderId());
        orderDetail.setStatus(0);
        orderDetailDao.updateOrderDetail(orderDetail);
        return success(null,"取消订单成功");
    }
    //确认订单状态
    @Transactional
    public  Response  confirmOrder(Order order){

        order.setOrderStatus(4);
        orderDao.updateOrder(order);
        return success(null,"确认订单成功");
    }
}
