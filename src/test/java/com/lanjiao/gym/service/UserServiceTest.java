package com.lanjiao.gym.service;

import com.lanjiao.gym.dao.UserDao;
import com.lanjiao.gym.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Test
    public void get(){
        System.out.println(userDao);
    }


//    @Test
//    public void findUserByIDAndName() {
//        User user =userService.findUserByIDAndName("2015","2015");
//        System.out.println(user.getUser_name());
//    }

    @Test
    public void findUserAll(){
        List<User> list = userService.findUserAll();
        if(list==null)
            System.out.println("null");
        else
        System.out.println(list.size());
    }
}