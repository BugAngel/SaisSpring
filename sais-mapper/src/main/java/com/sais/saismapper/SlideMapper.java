package com.sais.saismapper;

import com.sais.saisentity.Slide;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SlideMapper {
    Slide selectId(String id);
    ArrayList<Slide> selectAll();
    int delete(String id);
    int delAll(ArrayList<String> list);
    ArrayList<Slide> lists();
    ArrayList<Slide> listsLike(String keyword);
    int updateIntrocuduce(String college_e_name,String introduce);
    int updateSlide(Slide slide);
    int addSlide(Slide slide);
}
