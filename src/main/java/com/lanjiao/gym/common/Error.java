package com.lanjiao.gym.common;

/**
 * Created by zhangyi on 2017/12/19.
 */
public enum Error {
    // 枚举
    PARAM_MISS_ERROR(50000, "paramenters missing"),
    UNKNOW_SERVICE_NAME_ERROR(50001, "unknow service name"),
    UNKNOW_METHOD_NAME_ERROR(50002, "unknow method name"),
    OBJECT_EXISTED_ERROR(50003, "object existed"),
    OBJECT_NOT_EXISTED_ERROR(50004, "object not existed"),
    NOTING_NEED_TODO(50005, "nothing need to do"),
    INVALID_PARAM_ERROR(50006, "invalid parameters"),
    OUTER_SERVICE_CALL_ERROR(50007, "out service call error"),
    MICRO_SERVICE_CALL_ERROR(50008, "micro service call error"),
    STATE_ERROR(50009, "state error"),
    UNKNOW_ERROR(60000, "unknow error"),
    AUTH_ERROR(51000, "auth error");


    private int code;
    private String message;

    //getter & setter
    public int getCode() {
        return code;
    }

    public Error setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Error setMessage(String message) {
        this.message = message;
        return this;
    }

    //构造函数
    Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    //普通方法
    public static Error getErrorByCode(int code) {
        for (Error e: Error.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
