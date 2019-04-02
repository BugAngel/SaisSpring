package com.sais.saisweb.microblog;

import com.sais.saisentity.IndexBlog;
import com.sais.saisentity.Post;
import com.sais.saisservice.BlogService;
import com.sais.saisservice.PostService;
import com.sais.saisservice.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/microblog/comment")
public class CommentController {
    private PostService postService;
    private BlogService blogService;
    private UserService userService;

    public CommentController(PostService postService,BlogService blogService,UserService userService){
        this.postService=postService;
        this.blogService=blogService;
        this.userService=userService;
    }

    @RequestMapping({"/comment","/","/index"})
    public String comment(int post_id, Map<String,Object> result){
        IndexBlog indexBlog=new IndexBlog();
        Post post_info=postService.selectPostFromId(post_id);
        indexBlog.setAvatar(userService.selectAvatarFromId(post_info.getUser_id()));
        indexBlog=blogService.setCollect(indexBlog,post_info.getUser_id(),post_info.getId());
        indexBlog=blogService.getForward(indexBlog,post_info);
        result.put("post_info",indexBlog);
        List<Post> comment=postService.selectPostFromPid(post_id);
        ArrayList<IndexBlog> indexBlogs=new ArrayList<>();
        for(Post post : comment){
            IndexBlog vo=new IndexBlog();
            vo.setAvatar(userService.selectAvatarFromId(post.getUser_id()));
            vo=blogService.setCollect(vo,post.getUser_id(),post.getId());
            indexBlogs.add(vo);
        }
        result.put("datalists",indexBlogs);
        return "microblog/comment/comment";
    }
}
