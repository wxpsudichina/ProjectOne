package com.sudichina.shipperclient.bean;

/**
 * Created by mike on 2016/8/23.
 * 注册
 */
public class RegisterBean {
    private String accountName;
    private String accountPassword;
    private String accountType;
    private String source;

    public RegisterBean(String accountName, String accountPassword, String accountType, String source) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.accountType = accountType;
        this.source = source;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
