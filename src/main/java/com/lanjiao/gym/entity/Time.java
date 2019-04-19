package com.lanjiao.gym.entity;

import lombok.Data;

@Data
public class Time {

    private Integer id;


    private String timeId;


    private String begintime;


    private String endtime;

    //1可选，2不可选
    private Integer flag = new Integer(1);

}