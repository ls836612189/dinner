package com.tj.sys.response;

/**
 * Created by user on 2016/4/18.
 */
public enum EnumResponse {

    OPTION_SUCCESS("00000","操作成功!"),
    OPTION_FAILURE("00001","操作失败!"),
    LOGIN_VERFYING("10001","验证码错误,请重试!"),
    LOGIN_NO_USER("10002","用户名或密码错误,请重试!"),
    LOGIN_SUCCESS("10000","登录成功!");
    private String code;
    private String message;

    EnumResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
