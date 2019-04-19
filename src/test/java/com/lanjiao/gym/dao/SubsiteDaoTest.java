package com.lanjiao.gym.dao;

import com.alibaba.fastjson.JSONObject;
import com.lanjiao.gym.entity.Subsite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubsiteDaoTest {

    @Autowired
    SubsiteDao subsiteDao;
    @Test
    public void querySubsiteDetailBySiteIdAndSportId() {

    }


    @Test
    public void querySubsiteBySiteIdAndSportId() {
        List<Subsite> subsiteList = subsiteDao.querySubsiteBySiteIdAndSportId("#SP02","#SI01");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dudu",subsiteList);
        System.out.println(jsonObject);

    }
}