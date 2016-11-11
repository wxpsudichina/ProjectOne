package com.sudichina.shipperclient.bean;

/**
 * Created by mccccccmike on 2016/8/24.
 */
public class VerifyICBean {
    private String authTarget;
    private String authType;
    private String authCode;

    public VerifyICBean(String authTarget, String authType, String authCode) {
        this.authTarget = authTarget;
        this.authType = authType;
        this.authCode = authCode;
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

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
