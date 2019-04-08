package com.sais.saisweb.microblog;

import com.sais.saisentity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 论坛拦截器
 */
@Component
public class MicroBlogInterceptor implements HandlerInterceptor {
    /**
     *
     * @param httpServletRequest request 用于获取session，判断是否存在user，有没有被禁言
     * @param httpServletResponse 用于redirect
     * @param o 重写方法必须
     * @return 可以访问返回true，繁殖false
     * @throws Exception 暂无
     */
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
