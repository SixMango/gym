package com.lanjiao.gym.dao;

import com.alibaba.fastjson.JSONObject;
import com.lanjiao.gym.entity.ReservationDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationDetailsDaoTest {

    @Autowired
    ReservationDetailsDao reservationDetailsDao;
    @Test
    public void queryReservationDetailsBySubsiteIdAndReservationDate() {
        List<ReservationDetails> list = reservationDetailsDao.queryReservationDetailsBySubsiteIdAndReservationDate("#SD011","2019-04-14");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dudu",list);
        System.out.println(jsonObject);
    }
}