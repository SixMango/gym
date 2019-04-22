package com.lanjiao.gym.controller;

import com.lanjiao.gym.common.Response;
import com.lanjiao.gym.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sports")
public class SportController {

    @Autowired
    private SportService sportService;

    @RequestMapping("/querySports")
    public Response find(){
        return sportService.querySports();
    }
}
