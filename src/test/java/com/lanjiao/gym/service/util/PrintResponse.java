package com.lanjiao.gym.service.util;

import com.alibaba.fastjson.JSONObject;
import com.lanjiao.gym.common.Response;

import java.util.List;


public class PrintResponse {

    //打印Response数组
    public static void printResponseList(List<Response> list, Object[] test) {
        for (int i = 0; i < list.size(); i++) {
            Response response = list.get(i);
            printResponse(response, test[i]);
        }
    }

    //打印Response
    public static void printResponse(Response response, Object test) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("参数:" + test, response);
        System.out.println(jsonObject);
    }
    public static void printResponse(Response response) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("参数:  ", response);
        System.out.println(jsonObject);
    }
}
