package com.lanjiao.gym.service;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.common.ResponseService;
import com.lanjiao.gym.dao.SitesDao;
import com.lanjiao.gym.entity.Sites;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SitesService extends ResponseService {

    @Autowired
    private SitesDao sitesDao;

    public Response querySitesBySport(@Param("sportId")String sportId){
        if(sportId == null){
            return fail(50000,"参数是空");
        }
        List<Sites> list = sitesDao.querySitesBySport(sportId);
        if(list == null){
            return fail(50006,"数据库查询失败");
        }
        return success(list,"运动场查询成功");
    }

    public Response querySitesBySportId(@Param("sportId")String sportId){
        if(sportId == null){
            return fail(50000,"参数是空");
        }
        List<Sites> list = sitesDao.querySitesBySportId(sportId);
        if(list == null){
            return fail(50006,"数据库查询失败");
        }
        return success(list,"运动场查询成功");
    }

}
