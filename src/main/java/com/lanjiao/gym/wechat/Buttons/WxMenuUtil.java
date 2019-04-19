package com.lanjiao.gym.wechat.Buttons;


import com.alibaba.fastjson.JSONObject;
import com.lanjiao.gym.wechat.utils.HttpUtil;
import com.lanjiao.gym.wechat.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class WxMenuUtil {

	// 自定义菜单创建Url post请求
	private String createMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	@Autowired
	RedisUtil redisUtil;

	public JSONObject createMenu(String menu) {
		String access_token = redisUtil.get("global_token");
		System.out.println("服务器得到的数据00:  "+access_token);
		String url = createMenuUrl.replace("ACCESS_TOKEN", access_token);
		JSONObject jsonObject = null;
		try {
			jsonObject = HttpUtil.doPost(url, menu);
		}catch (IOException e){
			System.out.println("发生异常");
		}

		return jsonObject;
	}
}
