package com.lanjiao.gym.dao;

import com.lanjiao.gym.entity.WxUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface WxUserDao {


    List<WxUser> findWxUserList(WxUser wxUser);
    int insertWxUser(WxUser user);
    int updateWxUser(WxUser user);
}
