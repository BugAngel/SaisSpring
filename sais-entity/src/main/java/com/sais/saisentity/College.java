package com.sais.saisentity;

import lombok.Data;

/**
 * 学校实体类
 */
@Data
public class College {
    private int id; //主键
    private String college_name=""; //学校中文名，不重复
    private String college_e_name=""; //学校英文名，不重复
    private String country=""; //国家
    private String area=""; //地区
    private int qs_rank=0; //qs排名
    private Double rate=0.0; //录取率
    private String local_rank_name=""; //本国排名标准
    private int local_rank=0; //本国排名
    private Object hot_major=null; //热门专业
    private String icon=""; //图标
    private String introduce=""; //学校介绍
    private int sum=0; //总学生数
    private int undergraduate=0; //本科学生数
    private int graduate=0; //研究生学生数
    private Double student_staff_ratio=0.0; //师生比例
    private Double undergraduate_international_proportion=0.0; //国际生比例(本科)
    private Double graduate_international_proportion=0.0; //国际生比例(研究生)
    private String ea=""; //EA申请截止日期
    private String rd=""; //RD申请截止日期
    private String transfer=""; //转学申请截止日期
    private Double undergraduate_gpa=6.0; //本科平均GPA分数
    private int sat=9999; //本科SAT分数
    private Double undergraduate_language=100.0; //本科外语录取最低分
    private Double graduate_gpa=6.0; //研究生平均GPA分数
    private Double graduate_language=100.0; //研究生外语最低分
    private Object undergraduate_document=null; //本科生申请材料
    private Object graduate_document=null; //研究生申请材料
    private Object profession=null; //专业设置
    private String tuition_fee=""; //学费
    private String living_fee=""; //生活费
}
