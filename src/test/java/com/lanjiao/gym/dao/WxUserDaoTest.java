package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.User;
import com.lanjiao.gym.entity.WxUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WxUserDaoTest {

    @Autowired
    private WxUserDao wxUserDao;
    @Test
    public void findWxUserList() {
        WxUser wxUser = new WxUser();
        wxUser.setOpenid("111");
        List<WxUser> wxUserList = wxUserDao.findWxUserList(wxUser);
        for(WxUser user:wxUserList){
            System.out.println(user.getNickname());
        }
    }

    @Test
    public void insertWxUser() {
        WxUser wxUser = new WxUser();
        wxUser.setOpenid("111");
        wxUser.setNickname("dudu");
        wxUser.setSex(0);
        wxUserDao.insertWxUser(wxUser);
    }

    @Test
    public void updateWxUser() {
        WxUser wxUser = new WxUser();
        wxUser.setOpenid("111");
        wxUser.setSex(1);
        wxUserDao.updateWxUser(wxUser);
    }
}