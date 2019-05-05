package com.lanjiao.gym.controller;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.entity.WxUser;
import com.lanjiao.gym.service.OrderService;
import com.lanjiao.gym.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sites")
public class SiteController {

    @Autowired
    SiteService siteService;

    @Autowired
    OrderService orderService;


    @RequestMapping(value = "/querySitesBySport")
    public Response querySitesBySport(@RequestBody Map<String, Object> data) {
        String sportId = (String) data.get("sportId");

        Response response = siteService.querySitesBySport(sportId);
        return response;
    }

    @RequestMapping(value = "/querySiteBySportId")
    public Response querySitesBySportId(@RequestBody Map<String, Object> data) {
        String sportId = (String) data.get("sportId");

        Response response = siteService.querySitesBySportId(sportId);
        return response;
    }

}
