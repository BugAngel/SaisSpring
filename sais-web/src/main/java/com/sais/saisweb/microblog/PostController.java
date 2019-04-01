package com.sais.saisweb.microblog;

import com.alibaba.fastjson.JSON;
import com.sais.saisentity.*;
import com.sais.saisservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping({"/microblog/post"})
public class PostController {
    private PostService postService;
    private UserService userService;
    private AtService atService;
    private PraiseService praiseService;
    private CollectService collectService;

    @Autowired
    public PostController(PostService postService,UserService userService,AtService atService,PraiseService praiseService,CollectService collectService){
        this.postService=postService;
        this.userService=userService;
        this.atService=atService;
        this.praiseService=praiseService;
        this.collectService=collectService;
    }

    @ResponseBody
    @RequestMapping("/post")
    public String post(HttpServletRequest request,
                       @RequestParam(value = "pid",required = false,defaultValue = "0") String pidString,
                       @RequestParam(value = "content",required = false,defaultValue = "") String content,
                       @RequestParam(value = "pictures",required = false,defaultValue = "") String pictures,
                       @RequestParam(value = "type",required = false,defaultValue = "0") String typeString){

        int pid;
        int type;
        try{
            pid=Integer.parseInt(pidString);
            type=Integer.parseInt(typeString);
        }catch (Exception e){
            return JSON.toJSONString(0);
        }
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        int parent_user_id=0;
        if(user==null){
            return JSON.toJSONString(-1);
        }
        if(type==1){
            parent_user_id=postService.selectParentUserId(pid);
        }
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        int insert=postService.insertBlog(user.getAccount(), content, timestamp, user.getId(), pid, type, parent_user_id, pictures);
        if(insert<=0){
            return JSON.toJSONString(0);
        }
        int post_id=postService.getLastInsertId();
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
        String reg="/@([^@\\s]+)/";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(reg);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(content);
        Set<String> set=new HashSet<String>();
        while (m.find()){
            set.add(m.group());
        }
        for (String account : set) {
            int user_id=userService.selectIdFromAccount(account);
            if(user_id>0){
                atService.insertAt(user_id,post_id);
            }
        }
        return JSON.toJSONString(1);
    }

    @ResponseBody
    @RequestMapping("/praise")
    public String praise(HttpServletRequest request,@RequestParam(value = "post_id") String post_id_string){
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        int post_id=Integer.parseInt(post_id_string);
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

    @ResponseBody
    @RequestMapping("/collect")
    public String collect(HttpServletRequest request,@RequestParam(value = "post_id") String post_id_string){
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        int user_id=user.getId();
        int post_id=Integer.parseInt(post_id_string);
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

    @ResponseBody
    @RequestMapping("/getComment")
    public String getComment(HttpServletRequest request,@RequestParam(value = "pid") String pid_string){
        int pid=Integer.parseInt(pid_string);
        ArrayList<Post> posts=postService.selectCommentInfo(pid);
        ArrayList<IndexBlog> indexBlogs=new ArrayList<>();
        for(Post post : posts){
            IndexBlog indexBlog=new IndexBlog();
            indexBlog.setPost(post);
            User user=userService.selectId(post.getUser_id());
            indexBlog.setAvatar(user.getAvatar());
            indexBlog.setNickname(user.getNickname());
            indexBlogs.add(indexBlog);
        }
        return JSON.toJSONString(indexBlogs);
    }
}
