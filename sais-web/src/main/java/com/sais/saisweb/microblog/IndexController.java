package com.sais.saisweb.microblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/microblog","/microblog/index"})
public class IndexController {

    @RequestMapping({"/","/index"})
    public String index(){
        return "microblog/index/index";
    }
}
