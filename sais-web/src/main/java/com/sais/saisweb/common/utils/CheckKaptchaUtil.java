package com.sais.saisweb.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码工具类
 */
public class CheckKaptchaUtil {
    /**
     * 校对验证码
     * @param httpServletRequest Servlet请求
     * @return boolean表示验证为真或假
     */
    public static boolean kaptchaCheck(HttpServletRequest httpServletRequest, String code) {
        String rightCode = (String) httpServletRequest.getSession().getAttribute("rightCode");
        boolean flag=false;
        try {
            flag=rightCode.equals(code);
        }catch (Exception e){
            return false;
        }
        return flag;
    }
}
