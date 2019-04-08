package com.sais.saisweb.microblog;

import com.alibaba.fastjson.JSON;
import com.sais.saisentity.User;
import com.sais.saisservice.UserService;
import com.sais.saisweb.common.utils.CheckKaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Set;
import java.util.Map;

/**
 * 用户注册控制器
 */
@Controller
@RequestMapping("/microblog/register")
public class UserRegisterController {
    private UserService userService;
    @Value("${file.microblogHeadImagesPath}")
    private String microblogHeadImagesPath;

    @Autowired
    public UserRegisterController(UserService userService){
        this.userService=userService;
    }

    @GetMapping(value = {"/register","/index"})
    public String index(){
        return "microblog/login/register";
    }

    /**
     * 注册
     */
    @ResponseBody
    @PostMapping(value = "/checkRegister")
    public String checkLogin(HttpServletRequest request, String account, String password, String code){
        boolean captcha = CheckKaptchaUtil.kaptchaCheck(request,code);

        HashMap<String, Object> res = new HashMap<String, Object>();
        if (!captcha) {
            res.put("status", 0);
            res.put("message", "验证码错误");
            return JSON.toJSONString(res);
        }

        account = account.trim();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userService.selectAccount(account);
        if (user!=null) {
            res.put("status", 0);
            res.put("message", "账号已被注册");
            return JSON.toJSONString(res);
        } else {
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            user=new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setNickname(account);
            user.setAddtime(timestamp);
            user.setAvatar("avatar.jpg");
            user.setRecommend(userService.initializeRecommend());
            userService.register(user);
            res.put("status", 1);
            res.put("message", "注册成功!");
            return JSON.toJSONString(res);
        }
    }
}
