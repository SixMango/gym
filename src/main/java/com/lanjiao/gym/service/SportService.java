package com.lanjiao.gym.service;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.common.ResponseService;
import com.lanjiao.gym.dao.SportsDao;
import com.lanjiao.gym.entity.Sport;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService extends ResponseService {

    @Autowired
    private  SportsDao sportsDao;
    public Response querySports(){
        List<Sport> list = sportsDao.querySports();
        if(list == null){

            return fail(50006,"数据库查询失败");
        }
        return success(list,"运动场查询成功");
    }

    public Response querySportById(@Param("id") Integer id){
        List<Sport> list = sportsDao.querySportById(id);
        if(list == null){

            return fail(50006,"数据库查询失败");
        }
        return success(list,"运动场查询成功");
    }

    public Response querySportBySportId(@Param("sportId") String sportId){
        List<Sport> list = sportsDao.querySportBySportId(sportId);
        if(list == null){

            return fail(50006,"数据库查询失败");
        }
        return success(list,"运动场查询成功");
    }

    public Response querySportBySportName(@Param("sportName") String sportName){
        List<Sport> list = sportsDao.querySportBySportName(sportName);
        if(list == null){

            return fail(50006,"数据库查询失败");
        }
        return success(list,"运动场查询成功");
    }



}
