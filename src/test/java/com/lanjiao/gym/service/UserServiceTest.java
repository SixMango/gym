package com.lanjiao.gym.service;

import com.lanjiao.gym.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Autowired
    UserService userService;
    @Test
    public void findUserByIDAndName() {
        User user =userService.findUserByIDAndName("2015","2015");
        System.out.println(user.getUser_name());
    }

    @Test
    public void findUserAll(){
        List<User> list = userService.findUserAll();
        System.out.println(((List) list).size());
    }
}