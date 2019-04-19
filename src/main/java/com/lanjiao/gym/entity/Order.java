package com.lanjiao.gym.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private Integer id;
    private String orderId;
    private String userId;
    private Integer orderPrice;
    private Date orderDate;
    private Integer orderStatus;
    private Integer orderCheck;
    private Integer orderFree;
    private String orderRemake;
    private String orderFaceback;

}