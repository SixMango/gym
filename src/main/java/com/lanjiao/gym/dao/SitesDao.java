package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.Site;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SitesDao {

    List<Site> querySitesBySport(@Param("sportId") String sportId);
    List<Site> querySitesBySportId(@Param("sportId") String sportId);

}