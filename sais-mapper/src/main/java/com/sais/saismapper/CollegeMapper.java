package com.sais.saismapper;

import com.sais.saisentity.College;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Set;

/**
 * sais_college表操作接口
 */
@Repository
public interface CollegeMapper {
    ArrayList<College> selectAllInfo();
    ArrayList<College> selectRankInfo(int qsLow,int qsHigh,int localLow,int localHigh);
    ArrayList<College> selectCountryAndRankInfo(String country,int qsLow,int qsHigh,int localLow,int localHigh);
    College selectDetail(int id);
    ArrayList<College> selectLike(String like);
    Set<Integer> selectQsRanks();
}
