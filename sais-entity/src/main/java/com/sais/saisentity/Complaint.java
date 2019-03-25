package com.sais.saisentity;

import lombok.Data;

@Data
public class Complaint {
    private Integer id;
    private String content;
    private String mail;
    private String phone;

    public Complaint(){

    }

    public Complaint(String content,String mail,String phone){
        this.content=content;
        this.mail=mail;
        this.phone=phone;
    }
}
