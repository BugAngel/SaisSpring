package com.sais.saisentity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 管理员实体类
 */
@Data
public class Admin {
    private Integer id; //主键
    private String account; //账号
    private String password; //密码
    private String loginip; //上次登录IP
    private Timestamp logintime; //上次登录时间
}
