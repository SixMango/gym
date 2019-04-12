package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.Sports;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper

public interface SportsMapper {

//    @Select("select * from sports")
//    @Results({
//            @Result(id=true, column="id", property="id"),
//            @Result(column="sport_id", property="sportId"),
//            @Result(column="sport_name", property="sportName"),
//            @Result(column="image", property="image")
//    })
    List<Sports> findSports();
}