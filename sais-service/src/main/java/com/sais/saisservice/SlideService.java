package com.sais.saisservice;

import com.sais.saisentity.Slide;
import com.sais.saismapper.SlideMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SlideService {
    private SlideMapper slideMapper;

    @Autowired
    public SlideService(SlideMapper slideMapper){
        this.slideMapper=slideMapper;
    }

    public Slide selectId(String id){
        return slideMapper.selectId(id);
    }

    public ArrayList<Slide> selectAll(){
        return slideMapper.selectAll();
    }

    public int delete(String id){
        return slideMapper.delete(id);
    }

    public int delAll(ArrayList<String> list){
        return slideMapper.delAll(list);
    }

    public ArrayList<Slide> lists(){
        return slideMapper.lists();
    }

    public ArrayList<Slide> listsLike(String keyword){
        return slideMapper.listsLike(keyword);
    }

    public int updateIntrocuduce(String college_e_name,String introduce){
        return slideMapper.updateIntrocuduce(college_e_name,introduce);
    }

    public int updateSlide(Slide slide){
        return slideMapper.updateSlide(slide);
    }

    public int addSlide(Slide slide){
        return slideMapper.addSlide(slide);
    }
}
