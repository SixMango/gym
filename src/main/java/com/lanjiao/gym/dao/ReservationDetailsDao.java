package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.ReservationDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface ReservationDetailsDao {

    List<ReservationDetail> queryReservationDetailsBySubsiteIdAndReservationDate(@Param("subsiteId")String subsiteId, @Param("reservationDate")String reservationDate);

}