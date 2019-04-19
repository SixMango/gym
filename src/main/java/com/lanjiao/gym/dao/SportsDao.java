package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.Sports;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Component
public interface SportsDao {

    List<Sports> querySports();
    List<Sports> querySportById(@Param("id") Integer id);
    List<Sports> querySportBySportId(@Param("sportId") String sportId);
    List<Sports> querySportBySportName(@Param("sportName") String sportName);

}