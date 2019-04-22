package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.Sport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SportsDao {

    List<Sport> querySports();
    List<Sport> querySportById(@Param("id") Integer id);
    List<Sport> querySportBySportId(@Param("sportId") String sportId);
    List<Sport> querySportBySportName(@Param("sportName") String sportName);

}