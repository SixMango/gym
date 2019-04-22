package com.lanjiao.gym.entity;

import lombok.Data;

@Data
public class WxUser {

    private Integer id;
    private String openid;
    private String nickname;
    private Integer sex;
    private String  province;
    private String city;
    private String  country;
    private String headimgurl;
    private String privilege;
    private String unionid;
}