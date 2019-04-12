package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.Sites;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SitesMapper {

    @Select("select * from sites")
    @Results({
            @Result(id=true, column="id", property="id"),
            @Result(column="site_id", property="siteId"),
            @Result(column="site_name", property="siteName"),
    })
    List<Sites> findSites();
}