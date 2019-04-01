package com.sais.saisweb.microblog;

import com.alibaba.fastjson.JSON;
import com.sais.saisentity.IndexBlog;
import com.sais.saisentity.Post;
import com.sais.saisentity.User;

import com.sais.saisservice.CollectService;
import com.sais.saisservice.PostService;
import com.sais.saisservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

@RequestMapping({"/microblog/forward"})
@Controller
public class ForwardController {
    private PostService postService;
    private UserService userService;
    private CollectService collectService;

    @Autowired
    public ForwardController(PostService postService,UserService userService,CollectService collectService){
        this.postService=postService;
        this.userService=userService;
        this.collectService=collectService;
    }

    @RequestMapping({"/list"})
    public String forward(int post_id, Map<String,Object> result){
        IndexBlog indexBlog=new IndexBlog();
        Post post = postService.selectPostFromId(post_id);//查找post_id的微博内容
        indexBlog.setPost(post);
        User user=userService.selectId(post.getUser_id());//获取用户头像
        indexBlog.setAvatar(user.getAvatar());//头像赋值
        //收藏
        Integer integer=collectService.selectCollectStatus(post.getUser_id(),post.getId());
        if(integer==null){
            indexBlog.setCollect(0);
        }else {
            indexBlog.setCollect(integer);
        }

        //如果转发
        if(post.getPost_type()==2){
            Post parent=postService.selectPostFromId(post.getPid());
            StringBuilder content=new StringBuilder();
            boolean flag=true;
            while (flag){
                if(parent!=null && parent.getPost_type()==2){
                    content.append("@");
                    content.append(userService.selectNicknameFromId(parent.getUser_id()));
                    content.append(":");
                    content.append(parent.getContent());
                    content.append("//");
                    content.append(content);
                    //查找父级
                    parent=postService.selectPostFromId(parent.getPid());
                }else {
                    indexBlog.setParent(parent);
                    flag=false;
                }
            }
            if(content.length()>2){
                indexBlog.setParent_content(content.delete(content.length()-2,content.length()-1).toString());
            }
        }
        result.put("post_info",indexBlog);
        //获取所有回复数据
        ArrayList<Post> arrayList=postService.selectForward(post_id);
        ArrayList<IndexBlog> arrayList1=new ArrayList<>();
        for(Post post1 : arrayList){
            IndexBlog indexBlog1=new IndexBlog();
            indexBlog1.setPost(post1);
            indexBlog1.setAvatar(userService.selectAvatarFromId(post1.getUser_id()));
            arrayList1.add(indexBlog1);
        }
        result.put("datalists",arrayList1);
        return "/microblog/forward/forward_list";
    }

    @RequestMapping({"/getForward"})
    public String getForward(int pid, Map<String,Object> result){
        ArrayList<Post> posts=postService.selectForwardInfo(pid);
        ArrayList<IndexBlog> indexBlogs=new ArrayList<>();
        for(Post post : posts){
            IndexBlog indexBlog=new IndexBlog();
            indexBlog.setPost(post);
            User user=userService.selectId(post.getUser_id());
            indexBlog.setAvatar(user.getAvatar());
            indexBlog.setNickname(user.getNickname());
            indexBlogs.add(indexBlog);
        }
        int total=postService.getTotalForwardNum(pid);
        result.put("datalists",indexBlogs);
        result.put("total",total);
        return "/microblog/forward/forward";
    }
}
