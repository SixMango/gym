package com.lanjiao.gym.service;

import com.lanjiao.gym.common.ResponseService;
import org.springframework.stereotype.Service;
import com.lanjiao.gym.common.Response;

@Service
public class ErrorService extends ResponseService {

    //未登录时出现的错误
    public Response failHandle(int code ,String msg){
        return super.fail(code,msg);
    }

}
