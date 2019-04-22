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
public class SiteServiceTest {

    @Autowired
    private SiteService siteService;


    @Test
    public void querySiteBySport() {

        List<Response> list = new ArrayList<>();
        //测试参数
        String[] test = {"#SP01", "#SP05", "abcd"};
        for (int i = 0; i < test.length; i++) {
            Response response = siteService.querySitesBySport(test[i]);
            list.add(response);
        }
        PrintResponse.printResponseList(list, test);
    }


    @Test
    public void querySitesBySportId() {

        List<Response> list = new ArrayList<>();
        //测试参数
        String[] test = {"#SP01", "#SP05", "abcd"};
        for (int i = 0; i < test.length; i++) {
            Response response = siteService.querySitesBySportId(test[i]);
            list.add(response);
        }
        PrintResponse.printResponseList(list, test);
    }

}