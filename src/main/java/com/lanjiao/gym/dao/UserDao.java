package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {

    @Select("select * from user where user_id=#{user_id} and user_name=#{user_name}")
    User findUserByIdAndName(@Param("user_id") String user_id, @Param("user_name") String user_name);

    @Select("select * from user")
    List<User> findUserAll();
}
