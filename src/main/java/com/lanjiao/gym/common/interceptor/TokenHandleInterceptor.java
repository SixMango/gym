package com.lanjiao.gym.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.lanjiao.gym.entity.WxUser;
import com.lanjiao.gym.service.ErrorService;
import com.lanjiao.gym.wechat.utils.WxUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TokenHandleInterceptor implements HandlerInterceptor {

    //不能执行自动注入
    @Autowired
    private WxUserUtil wxUserUtil;

    @Autowired
    private ErrorService errorService;


    @Override
    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从头中得到token
        String token = request.getHeader("Authorization");

        if(token == null){
            log.info("用户未登录");
            response.setContentType("application/json;charset=utf-8");

            response.getWriter().write(JSON.toJSONString(errorService.failHandle(50000,"用户未未登录")));

            response.setStatus(401);

            return false;
        }



        WxUser wxUser = wxUserUtil.getWxUserByToken(token);

        if(wxUser == null){
            log.info("------------------getRequestURI:"+ request.getRequestURI()+"---------------------");
            log.info(" -----------------getRemoteHost:"+ request.getRemoteHost()+"---------------------");
            log.info("token已过期");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(errorService.failHandle(50000,"token已过期")));

            response.setStatus(403);
            return false;
        }
        return true;
    }

    @Override
    public  void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
