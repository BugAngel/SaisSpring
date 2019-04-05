package com.sais.saisweb.microblog;

import com.github.pagehelper.PageInfo;
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
    public String index(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,Map<String,Object> result){
        PageInfo page=postService.selectAllBlog(pageNum,5);
        List<Post> posts=page.getList();
        ArrayList<IndexBlog> datalists=new ArrayList<>();
        for(Post post : posts){
            IndexBlog indexBlog=new IndexBlog();
            indexBlog.setPost(post);
            indexBlog.setAvatar(userService.selectAvatarFromId(post.getUser_id()));
            indexBlog= blogService.getForward(indexBlog,post);
            datalists.add(indexBlog);
        }

        result.put("pageNum",page.getPageNum());//当前页数
        result.put("firstPage",page.getNavigateFirstPage());//第一页
        result.put("lastPage",page.getNavigateLastPage());//最后一页
        result.put("pages",page.getPages());//总页数
        result.put("total",page.getTotal());//记录总数
        result.put("url","microblog/index/index");//url
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
