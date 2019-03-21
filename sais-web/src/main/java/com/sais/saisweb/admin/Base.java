package com.sais.saisweb.admin;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

@Controller
public class Base {
    private final static Logger logger = LoggerFactory.getLogger(Base.class);
//    public Base(HttpServletRequest request){
//        isLogin(request);
//    }

    public String isLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        if((session.getAttribute("admin_id")==null) || (session.getAttribute("admin_account")==null)){
            return "admin/login/login";
        }
        return null;
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute("admin_id",null);
        request.getSession().setAttribute("admin_account",null);
        return "admin/login/login";
    }
}
