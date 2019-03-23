package com.sais.saisweb.admin;

import com.alibaba.fastjson.JSON;
import com.sais.saisentity.Admin;
import com.sais.saisservice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/admin/change_password")
public class ChangePasswordController {
    private AdminService adminService;

    @Autowired
    public ChangePasswordController(AdminService adminService){
        this.adminService=adminService;
    }

    @GetMapping(value = {"/change_password","index"})
    public String changePassword(){
        return "admin/change_password/change_password";
    }

    @ResponseBody
    @PostMapping(value = "/checkChangePassword")
    public String checkChangePassword(HttpServletRequest request,String old_password,String new_password) {
        old_password = DigestUtils.md5DigestAsHex(old_password.getBytes());
        new_password = DigestUtils.md5DigestAsHex(new_password.getBytes());
        HashMap<String, Object> res = new HashMap<String, Object>();
        Object admin_account=request.getSession().getAttribute("admin_account");
        if(admin_account==null){
            res.put("status", 0);
            res.put("message", "登录出错");
            return JSON.toJSONString(res);
        }
        String account=admin_account.toString();
        Admin admin=adminService.sel(account);

        if(old_password .equals(admin.getPassword())){  //检测原始密码是否正确
            int  result=adminService.changePassword(account,new_password);

            if(result== 0){
                res.put("status", 1);
                res.put("message", "更改成功");
                return JSON.toJSONString(res);
            }else{
                res.put("status", 0);
                res.put("message", "更改失败");
                return JSON.toJSONString(res);
            }
        }else{
            res.put("status", 0);
            res.put("message", "账号密码不匹配");
            return JSON.toJSONString(res);
        }
    }
}
