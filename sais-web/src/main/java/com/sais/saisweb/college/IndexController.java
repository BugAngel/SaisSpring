package com.sais.saisweb.college;

import com.sais.saisentity.Slide;
import com.sais.saisentity.User;
import com.sais.saisservice.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = {"/","/college/index","/college"})
public class IndexController {
    private SlideService slideService;

    @Autowired
    public IndexController(SlideService slideService){
        this.slideService=slideService;
    }

    @RequestMapping(value = {"/index","/"})
    public String index(HttpServletRequest request,Map<String, Object> result){
        User user=(User)request.getSession().getAttribute("user");
        Slide slide1,slide2,slide3;
//        if(user==null){
            slide1=slideService.selectSlide(1);
            slide2=slideService.selectSlide(2);
            slide3=slideService.selectSlide(3);
//        }else {
//
//        }

        result.put("img1",slide1);
        result.put("img2",slide2);
        result.put("img3",slide3);
        return "college/index/index";
    }
}
