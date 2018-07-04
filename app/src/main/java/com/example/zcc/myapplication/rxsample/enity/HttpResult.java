package com.example.zcc.myapplication.rxsample.enity;

/**
 *
 * 实体的基类
 */
public class HttpResult<T> {

    //用来模仿resultCode和resultMessage
    private int code;
    private String msg;
    //用来模仿Data
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
