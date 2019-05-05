package com.lanjiao.gym.wechat.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjiao.gym.dao.WxUserDao;
import com.lanjiao.gym.entity.WxUser;
import com.lanjiao.gym.util.SerializeUtil;
import com.lanjiao.gym.util.TokenRS256;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class WxUserUtil {


    @Autowired
    private Environment env;

    @Autowired
    private WxUserDao wxUserDao;

    @Autowired
    private RedisUtil redisUtil;

   //通过code换取网页授权access_token
   private String  getAccessToken = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
   //刷新access_token（如果需要）
   private String  getRefreshToken = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
   //拉取用户信息(需scope为 snsapi_userinfo)
   private String  getUserinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
   //检验授权凭证（access_token）是否有效
   private String  isAccess_token = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
    /**
     * 通过code，查询用户的信息，如果用户信息在数据库中存在，更新数据，反之添加用户信息到数据库
     */
   public WxUser getWxUser(String code){
       if(code == null || "".equals("")){
           return null;
       }
       String appId =  env.getProperty("AppId");
       String appSecret = env.getProperty("AppSecret");
       JSONObject jsonObject = null;
       try {
           //得到access_token 和用户 opneId
           jsonObject = HttpUtil.doGet(getAccessToken.replace("APPID",appId).replace("SECRET",appSecret).replace("CODE",code));
       } catch (IOException e) {
           log.info("微信获取网页token出错");
       }
       //获取token 和appId失败

       if(jsonObject == null){
           return null;
       }
       // 得到acces_token和openId
       String access_token = jsonObject.getString("access_token");
       String openid = jsonObject.getString("openid");
       JSONObject  userJson = null;

       try {
           //得到access_token 和用户 opneId
           userJson = HttpUtil.doGet(getUserinfo.replace("ACCESS_TOKEN",access_token).replace("OPENID",openid));
       } catch (IOException e) {
           log.info("微信获取网页userInfo出错");
       }
       //获取userInfo失败
       if(userJson ==null){
           return null;
       }


       WxUser wxuser = userJson.toJavaObject(WxUser.class);

       List<WxUser> wxUserList = wxUserDao.findWxUserList(wxuser);
       //通过access_token和openId查询用户信息，如果存在更新数据库，不存在插入数据；
       if(wxUserList != null && wxUserList.size() !=0){
           wxUserDao.updateWxUser(wxuser);
       }else{
           wxUserDao.insertWxUser(wxuser);
       }

       return wxuser;
   }


    /**
     * 通过code，查询用户的信息,返回字符串
     */
    public JSONObject getWxUserString(String code){

        String appId =  env.getProperty("AppId");
        String appSecret = env.getProperty("AppSecret");
        JSONObject jsonObject = null;
        try {
            //得到access_token 和用户 opneId
            jsonObject = HttpUtil.doGet(getAccessToken.replace("APPID",appId).replace("SECRET",appSecret).replace("CODE",code));
            System.out.println("code请求："+jsonObject);
        } catch (IOException e) {
            log.info("微信获取网页token出错");
        }

        //获取token 和appId，失败返回错误信息
        //{"errcode":40029,"errmsg":"invalid code"}
        if(jsonObject == null ||jsonObject.getInteger("errcode")!=null){
            return jsonObject;
        }
        // 得到acces_token和openId
        String access_token = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");

        JSONObject  userJson = null;

        try {
            //得到access_token 和用户 opneId
            userJson = HttpUtil.doGet(getUserinfo.replace("ACCESS_TOKEN",access_token).replace("OPENID",openid));

        } catch (IOException e) {
            log.info("微信获取网页userInfo出错");
        }
        //获取userInfo失败 {"errcode":40003,"errmsg":" invalid openid "}
        return userJson;
    }


    //通过token在redis中的到对象
    public WxUser getWxUserByToken(String token){

        if(token==null || "".equals(token)){
           return null;
        }
        //将token的第三部分
        String[] ss = token.split("\\.");
        String redisKey = ss[ss.length-1];
        //通过token得到objString
        String objString = redisUtil.get(redisKey);
        if(objString == null){
            return null;
        }
        System.out.println(objString);
        //将字节流转化为对象
        RSAKey rsaKey = SerializeUtil.getObjectFromString(objString,RSAKey.class);
        //字符串 ——> json ——>  java对象
        System.out.println(rsaKey);
        String usrString = TokenRS256.ValidToken(token,rsaKey);
        if(usrString == null){
            return null;
        }

        JSONObject userJson = JSON.parseObject(usrString);
        WxUser wxUser = userJson.toJavaObject(WxUser.class);
        return wxUser;
    }

}
