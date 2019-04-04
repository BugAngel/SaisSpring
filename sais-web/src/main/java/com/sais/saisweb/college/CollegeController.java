package com.sais.saisweb.college;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.sais.saisentity.College;
import com.sais.saisentity.User;
import com.sais.saisservice.CollegeService;
import com.sais.saisservice.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping(value = {"/college/college"})
public class CollegeController {
    private CollegeService collegeService;
    private SlideService slideService;

    @Autowired
    public CollegeController(CollegeService collegeService,SlideService slideService){
        this.collegeService=collegeService;
        this.slideService=slideService;
    }

    @RequestMapping(value = {"/index"})
    public String index(@RequestParam(value = "country",required = false,defaultValue = "all")String country,
                        @RequestParam(value = "qsLow",required = false,defaultValue = "1")int qsLow,
                        @RequestParam(value = "qsHigh",required = false,defaultValue = "150")int qsHigh,
                        @RequestParam(value = "majorLow",required = false,defaultValue = "1")int localLow,
                        @RequestParam(value = "majorHigh",required = false,defaultValue = "150")int localHigh,
                        Map<String,Object> result){
        ArrayList<College> datalists;
        if(country.equals("all")){
            datalists=collegeService.selectRankInfo(qsLow,qsHigh,localLow,localHigh);
        }else {
            datalists=collegeService.selectCountryAndRankInfo(country,qsLow,qsHigh,localLow,localHigh);
        }
        for(int i=0;i<datalists.size();i++){
            College college=datalists.get(i);
            college.setHot_major(Joiner.on('、').useForNull("").join(JSON.parseArray((String)college.getHot_major(),String.class)));
            datalists.set(i,college);
        }
        result.put("datalists",datalists);
        return "college/college/index";
    }

    @RequestMapping(value = {"/detail"})
    public String detail(HttpServletRequest request,int id, Map<String,Object> result){
        User user=(User)request.getSession().getAttribute("user");
        College college=collegeService.selectDetail(id);
        String banner=slideService.selectSlideFromCollegeEName(college.getCollege_e_name());

        int rank=college.getQs_rank();

        college.setHot_major(Joiner.on('、').useForNull("").join(JSON.parseArray((String)college.getHot_major(),String.class)));
        college.setUndergraduate_document(JSON.parseArray((String)college.getUndergraduate_document(),String.class));
        college.setGraduate_document(JSON.parseArray((String)college.getGraduate_document(),String.class));
        college.setProfession(JSON.parseArray((String)college.getProfession(),String.class));

        result.put("college_detail",college);
        result.put("banner",banner);
        return "college/college/detail";
    }

    @RequestMapping(value = {"/search"})
    public String search(@RequestParam(value = "like",required = false,defaultValue = "")String like,Map<String,Object> result){
        ArrayList<College> datalists=collegeService.selectLike(like);
        for(int i=0;i<datalists.size();i++){
            College college=datalists.get(i);
            college.setHot_major(Joiner.on('、').useForNull("").join(JSON.parseArray((String)college.getHot_major(),String.class)));
            datalists.set(i,college);
        }
        result.put("datalists",datalists);
        return "college/college/search";
    }
}
