package com.lanjiao.gym.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationDetail {

    private Integer id;

    private String reservationId;


    private String subsiteId;


    private Date reservationDate;


    private String timeId;

    private Time time;

}