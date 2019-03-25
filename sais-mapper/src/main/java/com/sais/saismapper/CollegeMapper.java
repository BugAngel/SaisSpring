package com.sais.saismapper;

import com.sais.saisentity.College;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CollegeMapper {
    @Select("select id,college_name,college_e_name,country,area,qs_rank,rate,local_rank_name,local_rank,hot_major,icon from sais_college order by qs_rank asc")
    ArrayList<College> selectAllInfo();
    @Select("select id,college_name,college_e_name,country,area,qs_rank,rate,local_rank_name,local_rank,hot_major,icon from sais_college where (qs_rank between #{qsLow} and #{qsHigh} ) and  (local_rank between #{localLow} and #{localHigh} ) order by qs_rank asc")
    ArrayList<College> selectRankInfo(String qsLow,String qsHigh,String localLow,String localHigh);
    @Select("select id,college_name,college_e_name,country,area,qs_rank,rate,local_rank_name,local_rank,hot_major,icon from sais_college where (country = #{country} ) and (qs_rank between #{qsLow} and #{qsHigh} ) and  (local_rank between #{localLow} and #{localHigh} ) order by qs_rank asc")
    ArrayList<College> selectCountryAndRankInfo(String country,String qsLow,String qsHigh,String localLow,String localHigh);
    @Select("select * from sais_college where id=#{id}")
    College selectDetail(int id);
    @Select("select * from sais_college where college_name like CONCAT('%',#{like},'%') order by qs_rank asc")
    ArrayList<College> selectLike(String like);
}
