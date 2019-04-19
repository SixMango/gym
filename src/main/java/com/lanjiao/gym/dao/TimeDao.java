package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.Time;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TimeDao{

    List<Time> queryTimes();
}