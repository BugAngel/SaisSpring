package com.sais.saisentity;

import lombok.Data;

/**
 * 幻灯片实体类
 */
@Data
public class Slide {
    private Integer id;
    private String college_e_name; //学校英文名
    private String introduce; //学校介绍
    private String picture; //图片
    private String college_name; //学校中文名

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
