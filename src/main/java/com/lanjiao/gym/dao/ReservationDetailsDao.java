package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.ReservationDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface ReservationDetailsDao {

    List<ReservationDetails> queryReservationDetailsBySubsiteIdAndReservationDate(@Param("subsiteId")String subsiteId, @Param("reservationDate")String reservationDate);

}