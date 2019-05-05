package com.lanjiao.gym.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjiao.gym.PrintFile;
import com.lanjiao.gym.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @Test
    public void queryOrderDetailBySubsiteIdAndDetailTimeAndTimeId(){
        String timeId = "#TI02";
        String subsiteId = "#SD001";
        String  detailTime = "2019-04-27";
        List<OrderDetail>  orderDetailList = orderDetailDao.queryOrderDetailBySubsiteIdAndDetailTimeAndTimeId(subsiteId,detailTime,timeId);
        System.out.println(orderDetailList.size());
        System.out.println(JSON.toJSONString(orderDetailList));

    }

    @Test
    public void queryOrderDetail(){

        OrderDetail od1 = new OrderDetail();
        od1.setSubsiteId("#SD005");
        od1.setTimeId("#TI01");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date detailTime = null;
        try{
            detailTime = simpleDateFormat.parse("2019-04-27");
        }catch (Exception e){
            System.out.println("日期格式转化出错");
        }
        od1.setDetailTime(detailTime);
        List<OrderDetail>  orderDetailList = orderDetailDao.queryOrderDetail(od1);
        PrintFile.println(JSON.toJSONString(orderDetailList),"子场地和日期.txt");

        System.out.println("查询完成");
    }

    @Test
    public  void updateOrderDetail(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus(0);
        orderDetail.setOrderId("104093495643340800");
        orderDetailDao.updateOrderDetail(orderDetail);

    }




}