package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.Sites;
import com.lanjiao.gym.entity.Subsite;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SitesDao {

    List<Sites> querySitesBySport(@Param("sportId") String sportId);
    List<Sites> querySitesBySportId(@Param("sportId") String sportId);

}