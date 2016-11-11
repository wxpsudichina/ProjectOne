package com.sudichina.shipperclient.bean;

/**
 * Created by mike on 2016/8/23.
 */
public class ResponseBean {
    private String msg;
    private Object data;
    private int code;
    private boolean success;

    public ResponseBean(String msg, Object data, int code, boolean success) {
        this.msg = msg;
        this.data = data;
        this.code = code;
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "[msg = " + msg + " data = " + data + " code = " + code + " success " + success + "]";
    }
}
