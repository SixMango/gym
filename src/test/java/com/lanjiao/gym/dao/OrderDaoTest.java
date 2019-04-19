package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.Order;
import com.lanjiao.gym.util.UniqueOrderGenerate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTest {

    @Autowired
    OrderDao orderDao;

    @Autowired
    UniqueOrderGenerate uniqueOrderGenerate;
    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setOrderFaceback("反馈信息");
        order.setOrderRemake("备注信息");
        order.setOrderCheck(1);
        order.setOrderDate(new Date());
        order.setOrderFree(1);
        order.setOrderPrice(200);
        String orderId = String .valueOf(uniqueOrderGenerate.nextId());
        order.setOrderId(orderId);
        order.setOrderStatus(1);
        order.setUserId("#US02");
        System.out.println(orderDao.insertOrder(order));
    }
}