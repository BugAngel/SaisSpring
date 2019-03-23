package com.sais.saisweb.admin;

import com.alibaba.fastjson.JSON;
import com.sais.saisentity.Admin;
import com.sais.saisservice.AdminService;
import com.sais.saisweb.common.utils.CheckKaptchaUtil;
import com.sais.saisweb.common.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/admin/login")
public class LoginController {
    private AdminService adminService;

    @Autowired
    public LoginController(AdminService adminService){
        this.adminService=adminService;
    }

    @GetMapping(value = {"/","/index","login"})
    public String index(){
        return "admin/login/login";
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
        boolean result = adminService.checkPassword(account,password);
        if (!result) {
            res.put("status", 0);
            res.put("message", "账号密码不匹配");
            return JSON.toJSONString(res);
        } else {
            Admin admin=adminService.sel(account);
            request.getSession().setAttribute("admin_id",admin.getId());
            request.getSession().setAttribute("admin_account",admin.getAccount());
            adminService.updateIP(admin.getAccount(),IpUtil.getIpAddr(request));
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
        request.getSession().setAttribute("admin_id",null);
        request.getSession().setAttribute("admin_account",null);
        return "admin/login/login";
    }
}
