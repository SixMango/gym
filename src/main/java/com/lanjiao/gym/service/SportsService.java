package com.lanjiao.gym.service;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.common.ResponseService;
import com.lanjiao.gym.dao.SportsMapper;
import com.lanjiao.gym.entity.Sports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportsService extends ResponseService {

    @Autowired
    SportsMapper sportsMapper;
    public Response findSports(){
        List<Sports> list = sportsMapper.findSports();
        return success(list,"运动场查询成功");
    }

}
