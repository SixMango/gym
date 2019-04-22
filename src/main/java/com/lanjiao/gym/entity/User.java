package com.lanjiao.gym.entity;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String userId;
    private String userName;
    private String userPassword;
    private String openid;

}