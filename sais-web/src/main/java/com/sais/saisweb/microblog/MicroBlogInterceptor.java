package com.sais.saisweb.microblog;

import com.sais.saisentity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class MicroBlogInterceptor implements HandlerInterceptor {
    //    在请求处理之前调用,只有返回true才会执行请求
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        User user=(User)session.getAttribute("user");
        if(user==null || user.getComment()==0){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/microblog/login/login");
            return false;
        }else{
            return true;
        }
    }
}
