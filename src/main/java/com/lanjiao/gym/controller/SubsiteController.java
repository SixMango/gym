package com.lanjiao.gym.controller;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.service.SubsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/subsite")
public class SubsiteController {
    @Autowired
    private SubsiteService subsiteService;
    @RequestMapping("/querySubsiteDetail")
    public Response querySubsiteDetail(@RequestBody Map<String, Object> data){

        String sportId = (String )data.get("sportId");
        String siteId = (String)data.get("siteId");
        String date = (String) data.get("curDate");

        Response response = subsiteService.querySubsiteDetailBySiteIdAndSportId(sportId,siteId,date);
        return response;
    }
}
