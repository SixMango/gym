package com.lanjiao.gym.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class OrderDetail {

    private Integer id;
    private String orderId;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date detailTime;
    private String subsiteId;
    private String timeId;
    private String  siteId;
    private String sportId;
    private Subsite subsite;
    private Time time;
    private Integer status;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", detailTime=" + detailTime +
                ", subsiteId='" + subsiteId + '\'' +
                ", timeId='" + timeId + '\'' +
                ", siteId='" + siteId + '\'' +
                ", sportId='" + sportId + '\'' +
                '}';
    }
}