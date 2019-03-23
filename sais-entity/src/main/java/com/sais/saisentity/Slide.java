package com.sais.saisentity;

import lombok.Data;

@Data
public class Slide {
    private Integer id;
    private String college_e_name;
    private String introduce;
    private String picture;

    public Slide(){
        college_e_name="";
        introduce="";
        picture="";
    }

    public Slide(String college_e_name,String introduce,String picture){
        this.college_e_name=college_e_name;
        this.introduce=introduce;
        this.picture=picture;
    }
}
