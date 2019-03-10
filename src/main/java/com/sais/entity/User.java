package com.sais.entity;

import java.sql.Timestamp;

public class User {
    private Integer id;
    private String account;
    private String password;
    private String nickname;
    private String loginip;
    private Timestamp logintime;
    private Timestamp addtime;

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public void setLogintime(Timestamp logintime) {
        this.logintime = logintime;
    }

    public void setAddtime(Timestamp addtime) {
        this.addtime = addtime;
    }

    public Integer getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLoginip() {
        return loginip;
    }

    public Timestamp getLogintime() {
        return logintime;
    }

    public Timestamp getAddtime() {
        return addtime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", passWord='" + password + '\'' +
                ", logintime='" + logintime.toString() + '\'' +
                '}';
    }
}
