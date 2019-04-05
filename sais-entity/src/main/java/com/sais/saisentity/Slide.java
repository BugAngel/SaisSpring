package com.sais.saisentity;

import lombok.Data;

@Data
public class Slide {
    private Integer id;
    private String college_e_name;
    private String introduce;
    private String picture;
    private String college_name;

    public Slide(){

    }

    public Slide(String college_e_name,String picture){
        this.college_e_name=college_e_name;
        this.picture=picture;
    }

    public Slide(String college_e_name,String introduce,String picture){
        this.college_e_name=college_e_name;
        this.introduce=introduce;
        this.picture=picture;
    }
}
