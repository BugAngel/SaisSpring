package com.sais.saismapper;

import com.sais.saisentity.Slide;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * sais_slide表操作接口
 */
@Repository
public interface SlideMapper {
    Slide selectId(int id);
    ArrayList<Slide> selectAll();
    int delete(int id);
    int delAll(ArrayList<Integer> list);
    ArrayList<Slide> lists();
    ArrayList<Slide> listsLike(String keyword);
    int updatePicture(String college_e_name,String picture);
    int addSlide(Slide slide);
    Slide selectQs(int qs);
    String selectSlideFromCollegeEName(String college_e_name);
}
