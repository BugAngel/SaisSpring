package com.sais.saisweb.microblog;

import com.sais.saisentity.IndexBlog;
import com.sais.saisentity.Post;
import com.sais.saisservice.BlogService;
import com.sais.saisservice.PostService;
import com.sais.saisservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/microblog","/microblog/index"})
public class MicroBlogIndexController {
    private UserService userService;
    private PostService postService;
    private BlogService blogService;

    @Autowired
    public MicroBlogIndexController(UserService userService, PostService postService, BlogService blogService){
        this.userService=userService;
        this.postService=postService;
        this.blogService = blogService;
    }

    @RequestMapping({"/","/index"})
    public String index(Map<String,Object> result){
        ArrayList<Post> posts=postService.selectAllBlog();
        ArrayList<IndexBlog> datalists=new ArrayList<>();
        for(Post post : posts){
            IndexBlog indexBlog=new IndexBlog();
            indexBlog.setPost(post);
            indexBlog.setAvatar(userService.selectAvatarFromId(post.getUser_id()));
            indexBlog= blogService.getForward(indexBlog,post);
            datalists.add(indexBlog);
        }
        result.put("datalists",datalists);
        return "microblog/index/index";
    }

    @RequestMapping({"/search"})
    public String search(@RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,Map<String,Object> result){
        List<Post> posts = postService.selectLikes(keyword);
        ArrayList<IndexBlog> indexBlogs=new ArrayList<>();
        for(Post post:posts){
            IndexBlog indexBlog=new IndexBlog();
            indexBlog.setAvatar(userService.selectAvatarFromId(post.getUser_id()));
            indexBlog=blogService.setCollect(indexBlog,post.getUser_id(),post.getId());
            indexBlog= blogService.setAllCount(indexBlog,post.getId());
            indexBlog=blogService.getForward(indexBlog,post);
            indexBlogs.add(indexBlog);
        }
        result.put("keyword",keyword);
        result.put("datalists",indexBlogs);
        return  "microblog/index/search";
    }
}
