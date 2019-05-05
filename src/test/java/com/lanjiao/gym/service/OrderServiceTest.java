package com.lanjiao.gym.service;

import com.alibaba.fastjson.JSON;
import com.lanjiao.gym.PrintFile;
import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.entity.Order;
import com.lanjiao.gym.entity.OrderDetail;
import com.lanjiao.gym.entity.WxUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;




    public void insertOrder1() throws ParseException {
        Order order = new Order();
        order.setOrderPrice(200);
        order.setOrderDate(new Date());
        order.setUserId("1314555");
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setTimeId("#TI02");
        orderDetail.setSubsiteId("#SD001");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String t = "2019-05-03";
        Date date = simpleDateFormat.parse(t);
        orderDetail.setDetailTime(date);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(orderDetail);

        order.setOrderDetailList(orderDetailList);

        Response response = orderService.insertOrder(order);

        System.out.println(JSON.toJSONString(response));
    }


    public void insertOrder2() throws ParseException {
        Order order = new Order();
        order.setOrderPrice(200);
        order.setUserId("1312321");
        order.setOrderDate(new Date());
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setTimeId("#TI02");
        orderDetail.setSubsiteId("#SD001");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String t = "2019-05-14";
        Date date = simpleDateFormat.parse(t);
        orderDetail.setDetailTime(date);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(orderDetail);

        order.setOrderDetailList(orderDetailList);

        Response response = orderService.insertOrder(order);

        System.out.println(JSON.toJSONString(response));
    }


    @Test
    public  void queryOrderByOrderStatus(){
        WxUser wxUser = new WxUser();
        wxUser.setOpenid("oGAwj1ZNtKa5rR714ABSPkZgGK9U");
        wxUser.setNickname("嘟嘟");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        Response response = orderService.queryOrderByOrderStatus(list,wxUser);
        PrintFile.println(JSON.toJSONString(response),"queryOrderByOrderStatus.txt");
        System.out.println("写入成功");
    }

    @Test
    public void queryOrder(){
        WxUser wxUser = new WxUser();
        wxUser.setOpenid("oGAwj1ZNtKa5rR714ABSPkZgGK9U");
        wxUser.setNickname("嘟嘟");
        Response response = orderService.queryOrder(wxUser);
        PrintFile.println(JSON.toJSONString(response),"queryOrder.txt");
        System.out.println("写入成功");

    }

    //@Test
    public void queryOrderByOrderId(){
        WxUser wxUser = new WxUser();
        wxUser.setOpenid("oGAwj1ZNtKa5rR714ABSPkZgGK9U");
        wxUser.setNickname("嘟嘟");
        Response response = orderService.queryOrderByOrderId("107957503928565760",wxUser);

        PrintFile.println(JSON.toJSONString(response),"queryOrderByOrderId.txt");

        System.out.println("写入成功");

    }
}