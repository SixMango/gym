package com.lanjiao.gym.controller;

import com.alibaba.fastjson.JSONObject;
import com.lanjiao.gym.entity.Sites;
import com.lanjiao.gym.service.SitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sites")
public class SitesController {

    @Autowired
    SitesService sitesService;

    @RequestMapping("/find")
    public JSONObject find(){

       List<Sites> list =  sitesService.findSubsite();
       JSONObject jsonObject = new JSONObject();
       jsonObject.put("sites",list);
       return jsonObject;
    }
}
