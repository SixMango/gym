package com.lanjiao.gym.entity;

import lombok.Data;

import java.util.List;

@Data
public class Subsite {

    private Integer id;

    private String subsiteId;

    private String siteId;

    private String sportId;

    private String subsiteDescription;

    private String subsiteName;

    private Double subsitePrice;


    private List<Time> timeList;

    private String sportName;

}