package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.User;

public interface UserMapper {

   User findUserByIdAndName(String id,String name);

}