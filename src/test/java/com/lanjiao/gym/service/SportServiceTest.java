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

@RunWith(SpringRunner.class)
@SpringBootTest
public class SportServiceTest {

    @Autowired
    SportService sportService;
    @Test
    public void querySports() {
        Response response = sportService.querySports();
        PrintResponse.printResponse(response,"所有运动场");
    }

    @Test
    public void querySportById() {
        List<Response> list = new ArrayList<>();
        Integer[] test = {-1, 1, 4, 10, 11};
        for (int i = 0; i < test.length; i++) {
            Response response = sportService.querySportById(1);
            list.add(response);
        }
        PrintResponse.printResponseList(list, test);
    }

    @Test
    public void querySportBySportId() {
        List<Response> list = new ArrayList<>();
        String[] test = {"SP01", "SP10", "SP11","abc"};
        for (int i = 0; i < test.length; i++) {
            Response response = sportService.querySportBySportId(test[i]);
            list.add(response);
        }
        PrintResponse.printResponseList(list, test);
    }

    @Test
    public void querySportBySportName() {
        List<Response> list = new ArrayList<>();
        String[] test = {"足球场", "足球", "SP11","abc"};
        for (int i = 0; i < test.length; i++) {
            Response response = sportService.querySportBySportName(test[i]);
            list.add(response);
        }
        PrintResponse.printResponseList(list, test);
    }
}