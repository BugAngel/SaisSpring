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
    private String comment;
}
