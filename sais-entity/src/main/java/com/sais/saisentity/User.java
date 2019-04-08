package com.sais.saisentity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户实体类
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String account; //账号
    private String password; //密码
    private String nickname; //昵称
    private String loginip; //上次登陆IP
    private Timestamp logintime; //上次登陆时间
    private Timestamp addtime; //添加时间
    private int comment=1; //禁言 1为可以发言 0为被禁言
    private int sex=0;  //性别 0保密 1男 2女
    private String qq="";
    private String email="";
    private String avatar; //头像
    private int posts_num=0; //发帖数
    private int follows_num=0; //转发数
    private int fans_num=0; //粉丝数
    private String phone="";
    private Double gpa=0.0;
    private int sat=0;
    private Double ielts=0.0;
    private Double toefl=0.0;
    private String recommend; //推荐学校JSON字符串 键为QS排名 值为推荐权重
}
