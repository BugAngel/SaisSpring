package com.sais.saisweb.college;

import com.alibaba.fastjson.JSON;
import com.sais.saisentity.Complaint;
import com.sais.saisservice.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/college/complain")
public class ComplainController {
    private ComplaintService complaintService;

    @Autowired
    public ComplainController(ComplaintService complaintService){
        this.complaintService=complaintService;
    }

    @ResponseBody
    @PostMapping(value = "/checkComplain")
    public String checkComplain(@RequestParam(value = "tsnr",required = false)String tsnr,
                                @RequestParam(value = "email",required = false)String email,
                                @RequestParam(value = "phone",required = false)String phone,
                                HttpServletRequest httpServletRequest)
    {
        HashMap<String,Object> res=new HashMap<String, Object>();
        HttpSession session = httpServletRequest.getSession(true);
        if(session.getAttribute("user")==null){
            res.put("status",2);
            res.put("message","您尚未登录，请先登录！");
            return JSON.toJSONString(res);
        }
        if (complaintService.insert(new Complaint(tsnr,email,phone))>0) {
            res.put("status",1);
            res.put("message","投诉成功，谢谢您的意见建议！");
            return JSON.toJSONString(res);
        } else {
            res.put("status",0);
            res.put("message","服务器繁忙，稍后再试！");
            return JSON.toJSONString(res);
        }
    }
}
