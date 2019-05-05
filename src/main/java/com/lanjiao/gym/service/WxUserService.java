package com.lanjiao.gym.service;


import com.alibaba.fastjson.JSON;
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


@Slf4j
@Service
public class WxUserService extends ResponseService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WxUserUtil wxUserUtil;

    public Response getToken(String code) {

        JSONObject wxUserJson = wxUserUtil.getWxUserString(code);
        if (wxUserJson == null) {
            return fail(50006, "得到openid和access_token出错");
        }
        //如果请求失败
        if (wxUserJson == null || wxUserJson.getInteger("errcode") != null) {
            return fail(50000, wxUserJson.toJSONString());
        }
        //得到userJson的token，并存储在redis中
        //得到wxUser ,过滤无用的信息，不存储在redis中
        WxUser wxUser = wxUserJson.toJavaObject(WxUser.class);
        String token = storeToken(JSON.toJSONString(wxUser));

        return success(wxUser, "token响应成功").setToken(token);
    }

    public Response getWxUserByToken(String token){

        WxUser wxUser = wxUserUtil.getWxUserByToken(token);

        return success(wxUser, "微信用户信息");
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
        //存入redis中，在加密过程中设置了过期时间为一天,在redis中设置过期时间为1小时
        redisUtil.set(redisKey, stringKey,60*60);
        return token;
    }

}
