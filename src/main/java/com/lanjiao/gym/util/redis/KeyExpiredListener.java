package com.lanjiao.gym.util.redis;

import java.nio.charset.StandardCharsets;
import java.util.List;

import com.lanjiao.gym.dao.OrderDao;
import com.lanjiao.gym.dao.OrderDetailDao;
import com.lanjiao.gym.entity.Order;
import com.lanjiao.gym.entity.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;


@Slf4j
public class KeyExpiredListener extends KeyExpirationEventMessageListener {

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderDetailDao orderDetailDao;

    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    //针对redis数据失效事件，进行数据处理
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel(),StandardCharsets.UTF_8);
        //过期的key
        String key = new String(message.getBody(),StandardCharsets.UTF_8);

        if(key.startsWith("order:")){
            log.info("order订单：  redis key 过期：pattern={},channel={},key={}",new String(pattern),channel,key);
            //得到过期的键值，更新数据库状态
            //order_status   1.待付款 2.已取消 3.未支付(过期）4.已付款
            // order_check 1.审核通过 ，2，审核未通过
            // order_free  0. 不免费 , 1.免费
            String orderId = key.replaceFirst("order:","");
            Order order = new Order();
            order.setOrderId(orderId);
            //通过主键查询订单
            List<Order> orderList = orderDao.queryOrder(order);
            Order orderByOrder = orderList.get(0);
            //如果订单的状态是待付款，更新状态为过期 ,并更新order_detail
            if(orderByOrder.getOrderStatus() == 1){
                orderByOrder.setOrderStatus(3);
                orderDao.updateOrder(orderByOrder);
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(orderId);
                orderDetail.setStatus(0);
                orderDetailDao.updateOrderDetail(orderDetail);
                log.info("订单"+ key +"已过期");
            }
        }

    }
}