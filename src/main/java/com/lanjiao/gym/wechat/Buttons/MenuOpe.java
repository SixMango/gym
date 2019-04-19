package com.lanjiao.gym.wechat.Buttons;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjiao.gym.wechat.Buttons.Button;
import com.lanjiao.gym.wechat.Buttons.ClickButton;
import com.lanjiao.gym.wechat.Buttons.ViewButton;
import com.lanjiao.gym.wechat.Buttons.Menu;
import com.lanjiao.gym.wechat.Buttons.WxMenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URLEncoder;


@Component
public class MenuOpe {

	String snsapi_base = "https://open.weixin.qq.com/connect/oauth2/"
			+ "authorize?appid=APPID&redirect_uri=REDIRECT_URI"
			+ "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";

	String snsapi_userinfo = "https://open.weixin.qq.com/connect/oauth2/authorize?"+
	"appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&"+
			"scope=snsapi_userinfo&state=STATE#wechat_redirect";
	public void createMenu() {
		WxMenuUtil wxMenuUtil = new WxMenuUtil();
		// 菜单

		ViewButton button11 = new ViewButton();
		button11.setName("预定场地");
		button11.setType("view");
		String appId =  "wx82140fcfdc9828a2";
		String redirectUri= "http://gym.ngrok.xiaomiqiu.cn/sports/querySports";

		try {
			redirectUri = URLEncoder.encode(redirectUri, "UTF-8");
		}catch (Exception e){
			System.out.println("转码失败");
		}

		button11.setUrl(snsapi_userinfo.replace("APPID", appId).replace("REDIRECT_URI", redirectUri));

		System.out.println(snsapi_userinfo.replace("APPID", appId).replace("REDIRECT_URI", redirectUri));
		ClickButton button12 = new ClickButton();
		button12.setName("今日入馆");
		button12.setType("click");
		button12.setKey("12");

		ClickButton button13 = new ClickButton();
		button13.setName("查询账户");
		button13.setType("click");
		button13.setKey("13");

		ClickButton button14 = new ClickButton();
		button14.setName("在线留言");
		button14.setType("click");
		button14.setKey("14");

		ClickButton button15 = new ClickButton();
		button15.setName("退卡须知");
		button15.setType("click");
		button15.setKey("15");

		Button button1 = new Button();
		button1.setName("我要");
		button1.setSub_button(new Button[] { button11, button12, button13, button14, button15 });

		ViewButton button21 = new ViewButton();
		button21.setName("收费标准");
		button21.setType("view");
		button21.setUrl("http://www.happy541.xyz/mywcp/");


		ViewButton button22 = new ViewButton();
		button22.setName("办卡须知");
		button22.setType("view");
		button22.setUrl("http://www.happy541.xyz/mywcp/");
	

		ViewButton button23 = new ViewButton();
		button23.setName("预定须知");
		button23.setType("view");
		button23.setUrl("http://www.baidu.com");

		ViewButton button24 = new ViewButton();
		button24.setName("入场须知");
		button24.setType("view");
		button24.setUrl("http://www.soso.com/");

		ClickButton button25 = new ClickButton();
		button25.setName("租赁须知");
		button25.setType("click");
		button25.setKey("111");

		Button button2 = new Button();
		button2.setName("用户手册");
		button2.setSub_button(new Button[] { button21, button22, button23, button24, button25 });

		ViewButton button31 = new ViewButton();
		button31.setName("场馆简介");
		button31.setType("view");
		button31.setUrl("http://tyb.lzjtu.edu.cn/");

		Menu menu = new Menu();
		menu.setButton(new Button[] { button1, button2, button31 });
		String menuObj = JSON.toJSONString(menu);
		System.out.println(menuObj);
		//JSONObject res = wxMenuUtil.createMenu(menuObj);
		FileWriter pw = null;
		try {

			File file = new File("G://毕设/tem.txt");
			pw = new FileWriter(file, false);
			pw.write(menuObj);

		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			try {
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println(res);
	}
	public static  void main(String[]args){
		MenuOpe menuOpe = new MenuOpe();
		menuOpe.createMenu();
	}


}
