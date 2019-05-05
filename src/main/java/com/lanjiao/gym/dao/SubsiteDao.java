package com.lanjiao.gym.dao;


import com.lanjiao.gym.entity.Subsite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SubsiteDao {

    List<Subsite> querySubsiteBySiteIdAndSportId(@Param("sportId")String sportId,@Param("siteId")String siteId);
    List<Subsite> querySubsiteBySubsiteId(@Param("subsiteId")String subsiteId);
}