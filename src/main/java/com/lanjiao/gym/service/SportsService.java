package com.lanjiao.gym.service;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.common.ResponseService;
import com.lanjiao.gym.dao.SportsDao;
import com.lanjiao.gym.entity.Sports;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportsService extends ResponseService {

    @Autowired
    private  SportsDao sportsDao;
    public Response querySports(){
        List<Sports> list = sportsDao.querySports();
        if(list == null){

            return fail(50006,"数据库查询失败");
        }
        return success(list,"运动场查询成功");
    }

    public Response querySportById(@Param("id") Integer id){
        List<Sports> list = sportsDao.querySportById(id);
        if(list == null){

            return fail(50006,"数据库查询失败");
        }
        return success(list,"运动场查询成功");
    }

    public Response querySportBySportId(@Param("sportId") String sportId){
        List<Sports> list = sportsDao.querySportBySportId(sportId);
        if(list == null){

            return fail(50006,"数据库查询失败");
        }
        return success(list,"运动场查询成功");
    }

    public Response querySportBySportName(@Param("sportName") String sportName){
        List<Sports> list = sportsDao.querySportBySportName(sportName);
        if(list == null){

            return fail(50006,"数据库查询失败");
        }
        return success(list,"运动场查询成功");
    }



}
