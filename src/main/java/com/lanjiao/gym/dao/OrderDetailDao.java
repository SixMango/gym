package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface OrderDetailDao {
    List<OrderDetail> queryOrderDetailBySubsiteIdAndDetailTime(@Param("subsiteId") String subsiteId, @Param("detailTime") String detailTime);
    int insertOrderDetail(OrderDetail orderDetail);
}