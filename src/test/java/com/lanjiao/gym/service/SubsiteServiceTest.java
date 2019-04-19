package com.lanjiao.gym.service;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.service.util.PrintResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SubsiteServiceTest {

    @Autowired
    SubsiteService subsiteService;

    @Test
    public void querySubsiteDetailBySiteIdAndSportId(){

        Response response = subsiteService.querySubsiteDetailBySiteIdAndSportId("#SP02","#SI01","2019-04-14");
        PrintResponse.printResponse(response);
    }

}