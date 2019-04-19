package com.lanjiao.gym.entity;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDetail {

    private Integer id;
    private String orderId;
    private Date detailTime;
    private String subsiteId;
    private String timeId;
    private String  siteId;
    private String sportId;

}