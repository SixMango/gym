package com.lanjiao.gym.controller;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.service.SportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sports")
public class SportsController {

    @Autowired
    SportsService sportsService;
    @RequestMapping("/querySports")
    public Response find(
            @RequestParam(value = "code",required = false) String code,
            @RequestParam(value="state",required=false) String state){

        if(code !=null){
            System.out.println(code);
        }
       // code=CODE&state=STATE
        return sportsService.querySports();
    }
}
