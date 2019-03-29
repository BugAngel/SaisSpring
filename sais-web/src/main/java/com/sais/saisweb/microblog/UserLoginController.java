package com.sais.saisweb.microblog;

import com.alibaba.fastjson.JSON;
import com.sais.saisentity.User;
import com.sais.saisservice.UserService;
import com.sais.saisweb.common.utils.CheckKaptchaUtil;
import com.sais.saisweb.common.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/microblog/login")
public class UserLoginController {
    private UserService userService;

    @Autowired
    public UserLoginController(UserService userService){
        this.userService=userService;
    }

    @GetMapping(value = {"/login","/index"})
    public String index(){
        return "microblog/login/login";
    }

    /**
     * 检查登录
     */
    @ResponseBody
    @PostMapping(value = "/checkLogin")
    public String checkLogin(HttpServletRequest request,String account,String password,String code){
        boolean captcha = CheckKaptchaUtil.kaptchaCheck(request,code);

        HashMap<String, Object> res = new HashMap<String, Object>();
        if (!captcha) {
            res.put("status", 0);
            res.put("message", "验证码错误");
            return JSON.toJSONString(res);
        }

        account = account.trim();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        boolean result = userService.checkPassword(account,password);
        if (!result) {
            res.put("status", 0);
            res.put("message", "账号密码不匹配");
            return JSON.toJSONString(res);
        } else {
            User user=userService.selectAccount(account);
            request.getSession().setAttribute("user",user);//注意session里的user用的是上次登录IP和登录时间
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            userService.login(user.getAccount(), IpUtil.getIpAddr(request),timestamp);
            res.put("status", 1);
            res.put("message", "登录成功!");
            return JSON.toJSONString(res);
        }
    }

    /**
     * 退出
     */
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute("user",null);
        return "microblog/login/login";
    }
}
