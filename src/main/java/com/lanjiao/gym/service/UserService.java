package com.lanjiao.gym.service;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.common.ResponseService;
import com.lanjiao.gym.dao.UserDao;
import com.lanjiao.gym.entity.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService extends ResponseService{

    @Autowired
    UserDao userDao;


    public Response findUserAll(){
        List<User> list=userDao.findUserAll();
        return success(list,"获取用户列表成功");
    }
}
