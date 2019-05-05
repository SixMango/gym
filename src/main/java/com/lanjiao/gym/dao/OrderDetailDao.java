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
    List<OrderDetail> queryOrderDetailBySubsiteIdAndDetailTimeAndTimeId(@Param("subsiteId") String subsiteId, @Param("detailTime") String detailTime,@Param("timeId") String timeId);
    List<OrderDetail> queryOrderDetailByOrderId(@Param("orderId")String orderId);
    int insertOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> queryOrderDetail(OrderDetail orderDetail);
    //根据orderId和status更新
    int updateOrderDetail(OrderDetail orderDetail);

}