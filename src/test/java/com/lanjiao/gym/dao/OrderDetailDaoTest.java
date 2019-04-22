package com.lanjiao.gym.dao;

import com.alibaba.fastjson.JSONObject;
import com.lanjiao.gym.entity.OrderDetail;
import com.lanjiao.gym.entity.ReservationDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    OrderDetailDao orderDetailDao;

    @Test
    public void queryOrderDetailBySubsiteIdAndDetailTime() {
        List<OrderDetail> list = orderDetailDao.queryOrderDetailBySubsiteIdAndDetailTime("#SD011","2019-04-17");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dudu",list);
        System.out.println(jsonObject);
    }
    @Test
    public void insertOrderDetail(){
        OrderDetail orderDetail = new OrderDetail();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "2019-4-17";
        Date detailTime = null;
        try{
            detailTime = simpleDateFormat.parse(date);
        }catch (Exception e){
            System.out.println("日期格式转化出错");
        }
        orderDetail.setTimeId("#TI02");
        orderDetail.setDetailTime(detailTime);
        orderDetail.setOrderId("104093495643340800");
        orderDetail.setSubsiteId("#SD015");
        orderDetail.setSiteId("#SI04");
        orderDetail.setSportId("#SP02");
        int col = orderDetailDao.insertOrderDetail(orderDetail);

        System.out.println(col);
    }
}