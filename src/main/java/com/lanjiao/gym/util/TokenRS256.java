package com.lanjiao.gym.util;

import com.lanjiao.gym.wechat.utils.TokenUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenRS256 {

    /**
     * 这个方法采取的是RS256 非对称加密算法
     */

    public static String TokenTest(String uid, RSAKey rsaJWK) {
        //获取生成token
        Map<String, Object> map = new HashMap<>();
        Date date = new Date();

        //建立载荷，这些数据根据业务，自己定义。
        map.put("uid", uid);
        //生成时间
        map.put("sta", date.getTime());
        //设置密码过期时间 1天
        map.put("exp", date.getTime() + (3600*1000*24));
        try {
            String token = TokenUtils.creatTokenRS256(map, rsaJWK);
            return token;
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }

    //处理解析的业务逻辑
    public static String ValidToken(String token, RSAKey rsaJWK) {
        //解析token
        String userString = null;
        try {
            if (token != null) {
                Map<String, Object> validMap = TokenUtils.validRS256(token, rsaJWK);
                int i = (int) validMap.get("Result");
                if (i == 0) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    log.info("==========token解析成功=========");
                    JSONObject jsonObject = (JSONObject) validMap.get("data");
                    userString = (String) jsonObject.get("uid");
                    String sta = simpleDateFormat.format(new Date((long) jsonObject.get("sta")));
                    String exp = simpleDateFormat.format(new Date((long) jsonObject.get("exp")));
                    log.info("================uid是：" + userString + "===========");
                    log.info("================生成时间是：" + sta + "===========");
                    log.info("================过期时间是：" + exp + "===========");

                } else if (i == 2) {
                    System.out.println("==========token已经过期==================");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }

        return userString;
    }

    public static void main(String[] args) throws JOSEException {
        //获取token
        String uid = "{\"id\":null,\"openid\":\"oGAwj1Zef-Xis4ANXUKigcDIImn4\",\"nickname\":\"running\",\"sex\":1,\"province\":\"\",\"city\":\"\",\"country\":\"阿尔及利亚\",\"headimgurl\":\"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epNv6w8aGFC9IYrkZE8LsqBsA2UrYWAVPFfRENpGVSBFKLQbgy9yz3XDAYpD2EnFnCgpNI9h0CU4g/132\",\"privilege\":\"[]\",\"unionid\":null}";
        //获取钥匙
        RSAKey key = TokenUtils.getKey();
        //获取token
        String token = TokenTest(uid, key);
        System.out.println("非对称加密的token：" + token);
        //解析token
        ValidToken(token,key);
    }


}
