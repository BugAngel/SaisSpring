package com.sais.saisweb.college;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/college/help")
public class HelpController {
    @RequestMapping(value = {"/about"})
    public String gonggong(){
        return "college/help/about";
    }

    @RequestMapping(value = {"/method"})
    public String kecheng(){
        return "college/help/method";
    }
}
