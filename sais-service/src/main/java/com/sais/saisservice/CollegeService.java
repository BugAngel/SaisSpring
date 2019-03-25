package com.sais.saisservice;

import com.sais.saisentity.College;
import com.sais.saismapper.CollegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CollegeService {
    private CollegeMapper collegeMapper;

    @Autowired
    public CollegeService(CollegeMapper collegeMapper){
        this.collegeMapper=collegeMapper;
    }

    public ArrayList<College> selectAllInfo(){
        return collegeMapper.selectAllInfo();
    }

    public College selectDetail(int id){
        return collegeMapper.selectDetail(id);
    }

    public ArrayList<College> selectRankInfo(String qsLow,String qsHigh,String localLow,String localHigh){
        return collegeMapper.selectRankInfo(qsLow,qsHigh,localLow,localHigh);
    }

    public ArrayList<College> selectCountryAndRankInfo(String country,String qsLow,String qsHigh,String localLow,String localHigh){
        return collegeMapper.selectCountryAndRankInfo(country,qsLow,qsHigh,localLow,localHigh);
    }

    public ArrayList<College> selectLike(String like){
        return collegeMapper.selectLike(like);
    }
}
