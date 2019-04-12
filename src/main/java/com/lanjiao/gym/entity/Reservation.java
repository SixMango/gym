package com.lanjiao.gym.entity;

import lombok.Data;

@Data
public class Reservation {

    private Integer id;
    private String reservationId;
    private String userId;
    private Integer reservationStatus;
    private Double reservationPrice;
    private String reservationRemake;

    private String reservationFeedback;

}