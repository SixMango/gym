package com.lanjiao.gym.common;

import java.io.Serializable;

/**
 * Created by zhangyi on 2017/12/22.
 */
public class Response implements Serializable {

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";

    private String status;
    private int code;
    private String msg;
    private Object result;

    private String sessionId;

    public Response() {
        status = SUCCESS;
        code = -1;
        msg = "";
    }

    public static Response success() {
        return new Response();
    }

    public static Response fail() {
        Response rr = new Response();
        rr.setStatus(FAIL);
        return rr;
    }

    public int getCode() {
        return code;
    }

    public Response setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Response setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public Response setResult(Object result) {
        this.result = result;
        return this;

    }

    public String getSessionId() {
        return this.sessionId;
    }

    public Response setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }
}
