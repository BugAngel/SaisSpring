package com.sais.saisentity;

import lombok.Data;

@Data
public class College {
    private int id;
    private String college_name="";
    private String college_e_name="";
    private String country="";
    private String area="";
    private int qs_rank=0;
    private float rate=0;
    private String local_rank_name="";
    private int local_rank=0;
    private Object hot_major=null;
    private String icon="";
    private String introduce="";
    private int sum=0;
    private int undergraduate=0;
    private int graduate=0;
    private float student_staff_ratio=0;
    private float undergraduate_international_proportion=0;
    private float graduate_international_proportion=0;
    private String ea="";
    private String rd="";
    private String transfer="";
    private String undergraduate_gpa="";
    private String sat="";
    private String undergraduate_language="";
    private String graduate_gpa="";
    private String graduate_language="";
    private Object undergraduate_document=null;
    private Object graduate_document=null;
    private Object profession=null;
    private String tuition_fee="";
    private String living_fee="";
}
