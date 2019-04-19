package com.lanjiao.gym.entity;

import lombok.Data;

import java.util.List;

@Data
public class Sites {

    private Integer id;
    private String siteId;
    private String siteName;
    private List<Subsite> subsiteList;
}