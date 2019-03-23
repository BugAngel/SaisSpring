package com.sais.saisentity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Admin {
    private Integer id;
    private String account;
    private String password;
    private String loginip;
    private Timestamp logintime;
}
