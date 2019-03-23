package com.sais.saisweb.admin;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AdminLoginInterceptor implements HandlerInterceptor {
    //    在请求处理之前调用,只有返回true才会执行请求
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        if((session.getAttribute("admin_id")==null) || (session.getAttribute("admin_account")==null)){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/admin/login/login");
            return false;
        }else{
            return true;
        }
    }
}
