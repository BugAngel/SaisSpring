package com.sais.saisweb.microblog;

import com.alibaba.fastjson.JSON;
import com.sais.saisentity.*;
import com.sais.saisservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping({"/microblog/post"})
public class PostController {
    private PostService postService;
    private UserService userService;
    private PraiseService praiseService;
    private CollectService collectService;

    @Autowired
    public PostController(PostService postService,UserService userService,PraiseService praiseService,CollectService collectService){
        this.postService=postService;
        this.userService=userService;
        this.praiseService=praiseService;
        this.collectService=collectService;
    }

    @RequestMapping("/post")
    public String post(HttpServletRequest request,
                       @RequestParam(value = "pid",required = false,defaultValue = "0") Integer pid,
                       @RequestParam(value = "content",required = false,defaultValue = "") String content,
                       @RequestParam(value = "type",required = false,defaultValue = "0") Integer type){
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        int parent_user_id=0;
        if(user==null){
            return JSON.toJSONString(-1);
        }
        if(type==1){
            parent_user_id=postService.selectUserIdFromId(pid);
        }
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        int insert=postService.insertBlog(user.getNickname(), content, timestamp, user.getId(), pid, type, parent_user_id);
        if(insert<=0){
            return JSON.toJSONString(0);
        }
        switch (type){
            case 0:
                userService.updatePostNum(user.getId());
                user.setPosts_num(user.getPosts_num()+1);
                session.setAttribute("user",user);
            break;
            case 1:
                postService.commentBlog(pid);
                break;
            case 2:
                userService.updatePostNum(user.getId());
                postService.forwardBlog(pid);
                break;
        }

        return JSON.toJSONString(1);
    }

    @RequestMapping("/praise")
    public String praise(HttpServletRequest request,@RequestParam(value = "post_id") Integer post_id){
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        int user_id=user.getId();
        Praise praise=praiseService.selectPraise(user_id,post_id);
        if(praise==null){
            praiseService.insertPraise(user_id,post_id);
            postService.praiseAdd(post_id);
            return JSON.toJSONString(1);
        }else {
            return JSON.toJSONString(0);
        }
    }

    @RequestMapping("/collect")
    public String collect(HttpServletRequest request,@RequestParam(value = "post_id") Integer post_id){
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        int user_id=user.getId();
        Collect collect=collectService.selectCollect(user_id,post_id);
        if(collect==null){//未被收藏
            collectService.insertCollect(user_id,post_id);
            return JSON.toJSONString(1);
        }else {
            if(collect.getStatus()==0){ //之前已取消收藏，现在要再次收藏
                collectService.updateCollect(user_id,post_id,1);
                return JSON.toJSONString(1);
            }else {
                collectService.updateCollect(user_id,post_id,0);
                return JSON.toJSONString(0);
            }
        }
    }
}
