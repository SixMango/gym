package com.lanjiao.gym.dao;

import com.alibaba.fastjson.JSON;
import com.lanjiao.gym.PrintFile;
import com.lanjiao.gym.entity.Order;
import com.lanjiao.gym.util.UniqueOrderGenerate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

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

    //@Test
    public void queryOrder(){

        Order order = new Order();
        List<Order> orderList = orderDao.queryOrder(order);
        PrintFile.println(JSON.toJSONString(orderList),"所有订单.txt");


        order.setOrderId("107957503928565760");
        List<Order> orderList1 = orderDao.queryOrder(order);
        PrintFile.println(JSON.toJSONString(orderList1),"orderId查询订单.txt");

        Order order1 = new Order();
        order1.setUserId("oGAwj1ZNtKa5rR714ABSPkZgGK9U");
        List<Order> orderList2 = orderDao.queryOrder(order1);
        PrintFile.println(JSON.toJSONString(orderList2),"用户查询订单.txt");

        order1.setOrderStatus(1);
        List<Order> orderList3 = orderDao.queryOrder(order1);
        PrintFile.println(JSON.toJSONString(orderList3),"用户和状态查询订单.txt");
        System.out.println("------------测试完成---------------");
    }

    //@Test
    public  void updateOrder(){
        //32		1314555	200	2019-04-27 15:49:06	1	2	1
        Order order = new Order();
        order.setOrderRemake("备注信息");
        order.setOrderCheck(666);
        order.setOrderFree(444);
        order.setOrderId("107976427025989632");
        order.setOrderStatus(555);

        int row = orderDao.updateOrder(order);

        System.out.println("影响的行数："+row);

    }
}