package com.lanjiao.gym.wechat.controller;


import com.lanjiao.gym.wechat.dispatcher.EventDispatcher;
import com.lanjiao.gym.wechat.dispatcher.MsgDispatcher;
import com.lanjiao.gym.wechat.utils.MessageUtil;
import com.lanjiao.gym.wechat.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/wechat")
public class WechatController {

    @RequestMapping(value = "/security", method= RequestMethod.GET)
    public void doGet(
            HttpServletResponse response,
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr) {
        response.setCharacterEncoding("UTF-8");
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                log.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/security",method = RequestMethod.POST)
    // post 方法用于接收微信服务端消息
    public void doPost(HttpServletRequest request,HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        try{
            Map<String, String> map= MessageUtil.parseXml(request);
            String msgtype=map.get("MsgType");
            String respXML = null; //
            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)){
                respXML = EventDispatcher.processEvent(map); //进入事件处理
            }else{
                respXML = MsgDispatcher.processMessage(map); //进入消息处理
            }
            PrintWriter out = response.getWriter();
            out.print(respXML);
            System.out.println(respXML);
            out.close();
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

}
