package com.sais.saisweb.microblog;

import com.alibaba.fastjson.JSON;
import com.sais.saisentity.User;
import com.sais.saisservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/microblog/setting")
public class SettingController {
    private UserService userService;

    @Autowired
    public SettingController(UserService userService){
        this.userService=userService;
    }

    @GetMapping(value = {"/setting","index"})
    public String index(){
        return "microblog/setting/setting";
    }

    @ResponseBody
    @PostMapping(value = {"/saveSetting"})
    public String saveSetting(HttpServletRequest request, String nickname, String sex, String qq, String email, String phone, String gpa, String sat, String ielts, String toefl){
        User user= (User)request.getSession().getAttribute("user");
        try{
            user.setNickname(nickname);
            user.setSex(Integer.parseInt(sex));
            user.setQq(qq);
            user.setEmail(email);
            user.setPhone(phone);
            if(!gpa.equals("")){
                user.setGpa(Float.parseFloat(gpa));
            }
            if(!sat.equals("")){
                user.setSat(Integer.parseInt(sat));
            }
            if(!ielts.equals("")){
                user.setIelts(Float.parseFloat(ielts));
            }
            if(!toefl.equals("")){
                user.setToefl(Float.parseFloat(toefl));
            }
        }catch (Exception e){
            return JSON.toJSONString("输入错误");
        }
        int res=userService.updateSetting(user);
        if(res>0){
            return JSON.toJSONString("设置成功");
        }else {
            return JSON.toJSONString("服务器繁忙");
        }
    }

    @ResponseBody
    @PostMapping(value = {"/changePassword"})
    public String changePassword(HttpServletRequest request,String old_password,String new_password){
        old_password = DigestUtils.md5DigestAsHex(old_password.getBytes());
        new_password = DigestUtils.md5DigestAsHex(new_password.getBytes());
        User user=(User)request.getSession().getAttribute("user");
        HashMap<String,Object> result=new HashMap<String,Object>();
        if(user==null){
            result.put("status",0);
            result.put("message","登录出错");
            return JSON.toJSONString(result);
        }

        if(old_password .equals(user.getPassword())){  //检测原始密码是否正确
            int  res=userService.changePassword(user.getAccount(),new_password);

            if(res>0){
                result.put("status",1);
                result.put("message","更改成功");
                request.getSession().setAttribute("user",null);
                return JSON.toJSONString(result);
            }else{
                result.put("status",0);
                result.put("message","更改失败");
                return JSON.toJSONString(result);
            }
        }else{
            result.put("status",0);
            result.put("message","账号密码不匹配");
            return JSON.toJSONString(result);
        }
    }
}
