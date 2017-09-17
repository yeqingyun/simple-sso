package com.yeqy.sso.biz;

import java.io.Serializable;

/**
 * Created by yeqy on 2017/9/13.
 */
public class User implements Serializable {

    private String account;
    private String password;

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
