package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;
    @Test
    public void findUserList() {
       List<User> userList = userDao.findUserList();
       for(User user:userList){
           System.out.println(user.getUserName());
       }

    }

    @Test
    public void insertUser() {

        User user = new User();
        user.setUserId("123789");
        user.setUserName("qyy");
        user.setUserPassword("12345678");
        user.setOpenid("1111");
        userDao.insertUser(user);
    }
}