package com.sais.saisweb.college;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 学校指导控制器
 */
@Controller
@RequestMapping("/college/guide")
public class GuideController {

    @RequestMapping(value = {"/gonggong"})
    public String gonggong(){
        return "college/guide/gonggong";
    }

    @RequestMapping(value = {"/kecheng"})
    public String kecheng(){
        return "college/guide/kecheng";
    }

    @RequestMapping(value = {"/xingqian"})
    public String xingqian(){
        return "college/guide/xingqian";
    }

    @RequestMapping(value = {"/xuexi"})
    public String xuexi(){
        return "college/guide/xuexi";
    }
}
