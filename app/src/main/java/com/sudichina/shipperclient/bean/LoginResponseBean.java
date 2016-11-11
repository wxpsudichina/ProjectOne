package com.sudichina.shipperclient.bean;

/**
 * Created by mike on 2016/8/23.
 */
public class LoginResponseBean {
    private String msg;
    private int code;
    private LoginDataBean data;
    private boolean success;

    public LoginResponseBean(String msg, int code, LoginDataBean data, boolean success) {
        this.msg = msg;
        this.code = code;
        this.data = data;
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LoginDataBean getData() {
        return data;
    }

    public void setData(LoginDataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
