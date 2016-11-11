package com.sudichina.shipperclient.bean;

/**
 * Created by SudiChina-105 on 2016/8/23.
 */
public class RequestICBean {
    private String authTarget;
    private String authType;
    private String authSource;

    public RequestICBean(String authTarget, String authType, String authSource) {
        this.authTarget = authTarget;
        this.authType = authType;
        this.authSource = authSource;
    }

    public String getAuthTarget() {
        return authTarget;
    }

    public void setAuthTarget(String authTarget) {
        this.authTarget = authTarget;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthSource() {
        return authSource;
    }

    public void setAuthSource(String authSource) {
        this.authSource = authSource;
    }
}
