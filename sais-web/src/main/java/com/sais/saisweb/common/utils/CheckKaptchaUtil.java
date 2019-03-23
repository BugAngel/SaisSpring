package com.sais.saisweb.common.utils;

import javax.servlet.http.HttpServletRequest;

public class CheckKaptchaUtil {
    /**
     * 校对验证码
     * @param httpServletRequest Servlet请求
     * @return boolean表示验证为真或假
     */
    public static boolean kaptchaCheck(HttpServletRequest httpServletRequest, String code) {
        String rightCode = (String) httpServletRequest.getSession().getAttribute("rightCode");

        return rightCode.equals(code);
    }
}
