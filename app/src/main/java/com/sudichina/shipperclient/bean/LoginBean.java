package com.sudichina.shipperclient.bean;

/**
 * Created by mike on 2016/8/23.
 */
public class LoginBean {
    private String accountName;
    private String accountPassword;

    public LoginBean(String accountName, String accountPassword) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
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

}
