package com.sais.saisentity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class User implements Serializable {
    private Integer id;
    private String account;
    private String password;
    private String nickname;
    private String loginip;
    private Timestamp logintime;
    private Timestamp addtime;
    private int comment=1;
    private int sex=0;
    private String qq="";
    private String email="";
    private String avatar;
    private int posts_num=0;
    private int follows_num=0;
    private int fans_num=0;
    private String phone="";
    private float gpa=0;
    private int sat=0;
    private float ielts=0;
    private float toefl=0;
    private String recommend;
}
