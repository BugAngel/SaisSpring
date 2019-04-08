package com.sais.saisentity;

import lombok.Data;

/**
 * 投诉实体类
 */
@Data
public class Complaint {
    private Integer id; //主键
    private String content; //投诉内容
    private String mail; //邮件
    private String phone; //电话

    public Complaint(){

    }

    public Complaint(String content,String mail,String phone){
        this.content=content;
        this.mail=mail;
        this.phone=phone;
    }
}
