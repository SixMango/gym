package com.lanjiao.gym.controller;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.service.WxUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/wxuser")
public class WxUserController {

    @Autowired
    WxUserService userService;


    @GetMapping("/userInfo")
    public Response getWxAuthorizedUser(@RequestHeader(value = "Authorization") String token){

        Response response = userService.getWxUserByToken(token);

        return response;
    }

    @PostMapping("/login")
    public Response getWxAuthorized(@RequestBody Map<String,String> map){

        String code = map.get("code");

        Response response = userService.getToken(code);

        return response;
    }
}
