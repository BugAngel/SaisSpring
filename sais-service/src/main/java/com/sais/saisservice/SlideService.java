package com.sais.saisservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sais.saisentity.Slide;
import com.sais.saismapper.SlideMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SlideService {
    private SlideMapper slideMapper;

    @Autowired
    public SlideService(SlideMapper slideMapper){
        this.slideMapper=slideMapper;
    }

    public Slide selectId(int id){
        return slideMapper.selectId(id);
    }

    public int delete(int id){
        return slideMapper.delete(id);
    }

    public int delAll(ArrayList<String> list){
        return slideMapper.delAll(list);
    }

    public ArrayList<Slide> lists(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return slideMapper.lists();
    }

    public PageInfo listsLike(String keyword, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Slide> list =  slideMapper.listsLike(keyword);
        return new PageInfo<>(list);
    }

    public int updatePicture(String college_e_name,String picture){
        return slideMapper.updatePicture(college_e_name, picture);
    }

    public int addSlide(Slide slide){
        return slideMapper.addSlide(slide);
    }

    public Slide selectSlide(int qs){
        Slide slide=slideMapper.selectQs(qs);
        if(slide.getIntroduce().length()>110){
            slide.setIntroduce(slide.getIntroduce().substring(0,110)+"...");
        }
        return slide;
    }

    public String selectSlideFromCollegeEName(String college_e_name){
        return slideMapper.selectSlideFromCollegeEName(college_e_name);
    }
}
