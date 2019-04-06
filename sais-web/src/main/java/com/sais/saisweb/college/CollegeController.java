package com.sais.saisweb.college;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.sais.saisentity.College;
import com.sais.saisentity.User;
import com.sais.saisservice.CollegeService;
import com.sais.saisservice.SlideService;
import com.sais.saisservice.UserService;
import com.sais.saisweb.common.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/college/college"})
public class CollegeController {
    private CollegeService collegeService;
    private SlideService slideService;
    private UserService userService;

    @Autowired
    public CollegeController(CollegeService collegeService,SlideService slideService,UserService userService){
        this.collegeService=collegeService;
        this.slideService=slideService;
        this.userService=userService;
    }

    @RequestMapping(value = {"/index"})
    public String index(@RequestParam(value = "country",required = false,defaultValue = "all")String country,
                        @RequestParam(value = "qsLow",required = false,defaultValue = "1")int qsLow,
                        @RequestParam(value = "qsHigh",required = false,defaultValue = "150")int qsHigh,
                        @RequestParam(value = "majorLow",required = false,defaultValue = "1")int localLow,
                        @RequestParam(value = "majorHigh",required = false,defaultValue = "150")int localHigh,
                        @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                        Map<String,Object> result){
        List<College> datalists;
        PageInfo page;
        if(country.equals("all")){
            page = collegeService.selectRankInfo(qsLow,qsHigh,localLow,localHigh,pageNum,10);
        }else {
            page = collegeService.selectCountryAndRankInfo(country,qsLow,qsHigh,localLow,localHigh,pageNum,10);
        }
        datalists=page.getList();
        for(int i=0;i<datalists.size();i++){
            College college=datalists.get(i);
            college.setHot_major(Joiner.on('、').useForNull("").join(JSON.parseArray((String)college.getHot_major(),String.class)));
            datalists.set(i,college);
        }

        result.putAll(PageUtil.setPageInfo(page,result));
        result.put("url","/college/college/index");//url
        result.put("datalists",datalists);
        return "college/college/index";
    }

    @RequestMapping(value = {"/detail"})
    public String detail(HttpServletRequest request,int id, Map<String,Object> result){
        User user=(User)request.getSession().getAttribute("user");
        College college=collegeService.selectDetail(id);
        String banner=slideService.selectSlideFromCollegeEName(college.getCollege_e_name());

        int rank=college.getQs_rank();
        Map<String,Double> recommend = JSON.parseObject(user.getRecommend(),new TypeReference<Map<String,Double>>() {});
        Double sum=0.0;
        for (Map.Entry<String, Double> entry : recommend.entrySet()) {
            Integer world_rank=Integer.parseInt(entry.getKey());
            Double add=100.0/Math.sqrt(2*3.14*10)*Math.exp(-(world_rank-rank)*(world_rank-rank)/(2.0*10));
            Double addValue=add+entry.getValue();
            sum+=addValue;
            recommend.put(entry.getKey(),addValue);
        }
        for (Map.Entry<String, Double> entry : recommend.entrySet()) {
            recommend.put(entry.getKey(),entry.getValue()*100/sum);
        }

        user.setRecommend(JSON.toJSONString(recommend));
        userService.updateRecommend(user);
        request.getSession().setAttribute("user",user);

        college.setHot_major(Joiner.on('、').useForNull("").join(JSON.parseArray((String)college.getHot_major(),String.class)));
        college.setUndergraduate_document(JSON.parseArray((String)college.getUndergraduate_document(),String.class));
        college.setGraduate_document(JSON.parseArray((String)college.getGraduate_document(),String.class));
        college.setProfession(JSON.parseArray((String)college.getProfession(),String.class));

        result.put("college_detail",college);
        result.put("banner",banner);
        return "college/college/detail";
    }

    @RequestMapping(value = {"/search"})
    public String search(@RequestParam(value = "like",required = false,defaultValue = "")String like,
                         @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                         Map<String,Object> result){
        PageInfo page=collegeService.selectLike(like,pageNum,10);
        List<College> datalists=page.getList();

        for(int i=0;i<datalists.size();i++){
            College college=datalists.get(i);
            college.setHot_major(Joiner.on('、').useForNull("").join(JSON.parseArray((String)college.getHot_major(),String.class)));
            datalists.set(i,college);
        }

        result.put("datalists",datalists);
        return "college/college/search";
    }
}
