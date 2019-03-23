package com.sais.saisentity;

import lombok.Data;

@Data
public class Complaint {
    private Integer id;
    private String content;
    private String mail;
    private String phone;
}
