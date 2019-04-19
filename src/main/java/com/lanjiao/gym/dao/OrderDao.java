package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderDao{

    int insertOrder(Order order);
}