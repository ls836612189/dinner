package com.tj.sys.response;

/**
 * Created by user on 2016/4/18.
 */
public class BaseResponse {

    private String code;
    private String message;

    public BaseResponse() {
    }
    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public BaseResponse(EnumResponse response) {
        this.code = response.getCode();
        this.message = response.getMessage();
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
