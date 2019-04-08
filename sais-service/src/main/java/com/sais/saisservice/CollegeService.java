package com.sais.saisservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sais.saisentity.College;
import com.sais.saismapper.CollegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 学校信息操作服务
 */
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

    public PageInfo selectRankInfo(int qsLow,int qsHigh,int localLow,int localHigh,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<College> list =  collegeMapper.selectRankInfo(qsLow,qsHigh,localLow,localHigh);
        return new PageInfo<>(list);
    }

    public PageInfo selectCountryAndRankInfo(String country,int qsLow,int qsHigh,int localLow,int localHigh,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<College> list =  collegeMapper.selectCountryAndRankInfo(country,qsLow,qsHigh,localLow,localHigh);
        return new PageInfo<>(list);
    }

    public PageInfo selectLike(String like,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<College> list =  collegeMapper.selectLike(like);
        return new PageInfo<>(list);
    }
}
