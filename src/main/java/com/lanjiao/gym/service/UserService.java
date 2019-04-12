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

//    public User findUserByIDAndName(String user_id,String user_name){
//        User user = null;
//        user = userDao.findUserByIdAndName(user_id,user_name);
//        return user;
//    }

    public Response findUserAll(){
        List<User> list=userDao.findUserAll();
        return success(list,"获取用户列表成功");
    }
}
