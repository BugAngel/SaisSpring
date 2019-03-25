package com.sais.saisweb.college;

import com.sais.saisentity.Slide;
import com.sais.saisservice.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String index(Map<String,Object> result){
        Slide slide1=slideService.selectQs(1);
        Slide slide2=slideService.selectQs(2);
        Slide slide3=slideService.selectQs(3);
        result.put("img1",slide1);
        result.put("img2",slide2);
        result.put("img3",slide3);
        return "college/index/index";
    }
}
