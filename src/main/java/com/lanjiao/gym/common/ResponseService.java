package com.lanjiao.gym.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by zhangyi on 2017/12/22.
 */
@Component
public class ResponseService {
    public Response success(Object result, String msg) {
        return Response.success().setResult(result).setMsg(msg);
    }

    public Response fail(int code, String msg) {
        return Response.fail().setCode(code).setMsg(msg);
    }

    /**
     *
     * @param data  需要封装的数据
     * @param codeString 数据字典中代表错误码的key值
     * @param statusString 数据词典中代表状态信息的key值
     * @param msgString 数据词典中代表返回消息的key值
     * @param successValue 状态消息为成功时对应的值，默认为"success"
     * @return 返回RestResponse对象
     *
     * 该方法主要是为了适应不同的provide返回的不同的结果集封装成相对统一的输出
     */
    public Response result(Map<String, String> data, String codeString, String statusString, String msgString, String successValue) {
        Response result;
        String msg = null;
        int code = -1;
        String status = null;
        if (data.containsKey(msgString)){
            msg = data.get(msgString);
            data.remove(msgString);
        }
        if (data.containsKey(codeString)){
            code = Integer.parseInt(data.get(codeString));
            data.remove(codeString);
        }
        if (data.containsKey(statusString)){
            status = data.get(statusString);
            data.remove(statusString);
        }
        if(status == null){
            result = success(data, msg);
        } else if(status == (successValue == null ? Response.SUCCESS : successValue)){
            result = success(data, msg);
        } else {
            result = fail(code, msg);
        }
        return result;
    }
}
