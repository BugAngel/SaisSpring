package com.sais.saisweb.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Login {
    /**
     * 检查登录
     */
    @RequestMapping(value = "/checkLogin",method = RequestMethod.GET)
    public String checkLogin(HttpServletRequest request){
        request.getSession().setAttribute("admin_id",null);
        request.getSession().setAttribute("admin_account",null);
        return "admin/login/login";
    }
}
