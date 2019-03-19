package com.sais.saisentity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Integer id;
    private String account;
    private String password;
    private String nickname;
    private String loginip;
    private Timestamp logintime;
    private Timestamp addtime;

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
