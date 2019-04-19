package com.lanjiao.gym.wechat.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName TokenUtil
 * @Description //TODO
 * @Date 2019/4/1
 * @CreateBy zhang
 */
@Slf4j
@Component
public class TokenUtil {

    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Environment env;

    // 获取access_token的接口地址（GET） 限2000（次/天）
    private String tokenUrl=null;


   @Scheduled(initialDelay = 1000, fixedDelay = 7000*1000 )
    public void getToken(){
        //访问微信服务器的地址
        tokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                +env.getProperty("AppId")+"&secret="+env.getProperty("AppSecret");
        //获取一个json对象
        JSONObject json = null;
        try {
            log.info("==============开始获取access_token===============");
            json = httpUtil.doGet(tokenUrl);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
        String access_token = null;
        //判断json是否为空
        if (json!=null){
            //将获取的access_token放入accessToken对象中
            access_token=json.getString("access_token");
            log.info("access_token:"+access_token);
            redisUtil.set("global_token", access_token);
            log.info("==============写入access_token成功===============");
        }else {
            log.info("==============获取access_token失败===============");
            //此处可能陷入死循环
            getToken();
        }
    }
}