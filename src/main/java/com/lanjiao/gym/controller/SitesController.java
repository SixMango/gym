package com.lanjiao.gym.controller;

import com.alibaba.fastjson.JSONObject;
import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.common.ResponseService;
import com.lanjiao.gym.entity.Sites;
import com.lanjiao.gym.service.SitesService;
import org.apache.http.protocol.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sites")
public class SitesController {

    @Autowired
    SitesService sitesService;
    @RequestMapping(value = "/querySitesBySport")
    public Response querySitesBySport(@RequestBody Map<String, Object> data) {
        String sportId = (String) data.get("sportId");

        Response response = sitesService.querySitesBySport(sportId);
        return response;
    }

    @RequestMapping(value = "/querySitesBySportId")
    public Response querySitesBySportId(@RequestBody Map<String, Object> data) {
        String sportId = (String) data.get("sportId");

        Response response = sitesService.querySitesBySportId(sportId);
        return response;
    }
}
