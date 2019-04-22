package com.lanjiao.gym.service;


import com.alibaba.fastjson.JSONObject;
import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.common.ResponseService;
import com.lanjiao.gym.entity.WxUser;
import com.lanjiao.gym.util.SerializeUtil;
import com.lanjiao.gym.util.TokenRS256;
import com.lanjiao.gym.util.TokenUtils;
import com.lanjiao.gym.wechat.utils.RedisUtil;
import com.lanjiao.gym.wechat.utils.WxUserUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class WxUserService extends ResponseService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WxUserUtil wxUserUtil;

    public Response getToken(String code) {
        if (code == null || "".equals(code)) {
            return fail(50000, "code不能为空");
        }
        JSONObject wxUserJson = wxUserUtil.getWxUserString(code);
        if (wxUserJson == null) {
            return fail(50006, "得到openid和access_token出错");
        }
        //如果请求失败
        if (wxUserJson == null || wxUserJson.getInteger("errcode") != null) {
            return fail(50000, wxUserJson.toJSONString());
        }
        //得到userJson的token，并存储在redis中
        String token = storeToken(wxUserJson.toString());
        return success(token, "token响应成功");
    }

    public Response getWxUserByToken(String token) {
        if (token == null || "".equals(token)) {
            return fail(50006, "token不能为空");
        }
        WxUser wxUser = wxUserUtil.getWxUserByToken(token);
        if (wxUser == null) {
            return fail(50009, "token已过期,重新登录");
        }
        return success(wxUser, "微信用户信息");
    }


    public Response getWxUserAndToken(String token, String code) {

        WxUser wxUser = null;
        Map<String, Object> map = new HashMap<>();
        if (token != null && "".equals(token)) {
            wxUser = wxUserUtil.getWxUserByToken(token);
        }
        if (wxUser != null) {
            map.put("token", token);
            map.put("userInfo", wxUser);
            return success(map, "微信用户信息和token");
        }
        if (code == null || "".equals(code)) {
            return fail(50000, "token和code不能同为空");
        }
        JSONObject wxUserJson = wxUserUtil.getWxUserString(code);
        if (wxUserJson == null) {
            return fail(50006, "得到openid和access_token出错");
        }
        //如果请求失败
        if (wxUserJson == null || wxUserJson.getInteger("errcode") != null) {
            return fail(50000, wxUserJson.toJSONString());
        }

        token = storeToken(wxUserJson.toString());
        WxUser wxUser1 = wxUserJson.toJavaObject(WxUser.class);
        return success(wxUser1, "微信用户信息").setToken(token);
    }

    //将token加密，并存储在radius
    private String storeToken(String wxuserString){

        RSAKey rsakey = null;
        try {
            //获取密钥
            rsakey = TokenUtils.getKey();
        } catch (JOSEException e) {
            log.info("获取密钥出错");
        }
        //将对象加密成token
        String token = TokenRS256.TokenTest(wxuserString, rsakey);
        //将token的第三部分作为key
        String[] ss = token.split("\\.");
        String redisKey = ss[ss.length - 1];
        //序列化RSAKey对象
        String stringKey = SerializeUtil.object2String(rsakey);
        //存入redis中，有效时间1小时
        redisUtil.set(redisKey, stringKey, 3600 * 1000);
        return token;
    }

}
