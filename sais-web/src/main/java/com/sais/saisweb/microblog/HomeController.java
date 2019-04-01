package com.sais.saisweb.microblog;

import com.sais.saisentity.IndexBlog;
import com.sais.saisentity.Post;
import com.sais.saisentity.User;
import com.sais.saisservice.PostService;
import com.sais.saisservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping({"/microblog/home"})
public class HomeController {
    private UserService userService;
    private PostService postService;

    @Autowired
    public HomeController(UserService userService,PostService postService){
        this.userService=userService;
        this.postService=postService;
    }

    @RequestMapping({"/","/index"})
    public String index(HttpServletRequest request,Map<String, Object> result){
        User user=(User) request.getSession().getAttribute("user");
        ArrayList<Post> posts=postService.selectUserBlog(user.getId());
        ArrayList<IndexBlog> datalists=new ArrayList<>();
        for(Post post : posts){
            IndexBlog indexBlog=new IndexBlog();
            indexBlog.setPost(post);
            indexBlog.setAvatar(userService.selectAvatarFromId(post.getUser_id()));
            indexBlog.setNickname(userService.selectNicknameFromId(post.getUser_id()));

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
            datalists.add(indexBlog);
        }
        result.put("datalists",datalists);
        return "microblog/home/index";
    }
}
