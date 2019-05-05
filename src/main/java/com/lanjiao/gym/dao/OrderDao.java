package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OrderDao{

    int insertOrder(Order order);
    int updateOrder(Order order);
    List<Order> queryOrder(Order order);
    List<Order> queryOrderByOrderId(Order order);

}