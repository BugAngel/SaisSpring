package com.sais.saisweb.college;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sais.saisentity.Slide;
import com.sais.saisentity.User;
import com.sais.saisservice.SlideService;
import com.sais.saisweb.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 主界面控制器
 */
@Controller
@RequestMapping(value = {"/","/college/index","/college"})
public class IndexController {
    private SlideService slideService;

    @Autowired
    public IndexController(SlideService slideService){
        this.slideService=slideService;
    }

    @RequestMapping(value = {"/index","/"})
    public String index(HttpServletRequest request,Map<String, Object> result){
        User user=(User)request.getSession().getAttribute("user");
        Slide slide1,slide2,slide3;
        if(user==null){
            slide1=slideService.selectSlide(1);
            slide2=slideService.selectSlide(2);
            slide3=slideService.selectSlide(3);
        }else {
            Map<String,Double> recommend = JSON.parseObject(user.getRecommend(),new TypeReference<Map<String,Double>>() {});
            List<Map.Entry<String, Double>> list = new ArrayList<>(recommend.entrySet());

            Collections.sort(list,new Comparator<Map.Entry<String,Double>>() {
                //降序排序
                public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            //将权重最高的三个幻灯片及介绍显示在主界面
            slide1=slideService.selectSlide(Integer.parseInt(list.get(0).getKey()));
            slide2=slideService.selectSlide(Integer.parseInt(list.get(1).getKey()));
            slide3=slideService.selectSlide(Integer.parseInt(list.get(2).getKey()));
        }

        result.put("img1",slide1);
        result.put("img2",slide2);
        result.put("img3",slide3);
        return "college/index/index";
    }

    //页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav=new ModelAndView("college/index/index");
        mav.addObject("cid", cid);
        return mav;
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public String pushToWeb(@PathVariable String cid, String message) {
        HashMap<String, Object> res = new HashMap<>();
        try {
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            e.printStackTrace();
            res.put("status", 0);
            res.put("message", "验证码错误");
            return JSON.toJSONString(res);
        }
        res.put("status", 1);
        res.put("message", "ojbk");
        return JSON.toJSONString(res);
    }

}
