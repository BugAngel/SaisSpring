package com.sais.saisweb.microblog;

import com.github.pagehelper.PageInfo;
import com.sais.saisentity.IndexBlog;
import com.sais.saisentity.Post;
import com.sais.saisentity.User;

import com.sais.saisservice.CollectService;
import com.sais.saisservice.BlogService;
import com.sais.saisservice.PostService;
import com.sais.saisservice.UserService;
import com.sais.saisweb.common.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 转发界面与操作控制器
 */
@RequestMapping({"/microblog/forward"})
@Controller
public class ForwardController {
    private PostService postService;
    private UserService userService;
    private CollectService collectService;
    private BlogService blogService;

    @Autowired
    public ForwardController(PostService postService, UserService userService, CollectService collectService, BlogService blogService){
        this.postService=postService;
        this.userService=userService;
        this.collectService=collectService;
        this.blogService = blogService;
    }

    @RequestMapping({"/list"})
    public String forward(int post_id, Map<String,Object> result,
                          @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
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
        indexBlog= blogService.getForward(indexBlog,post);
        result.put("post_info",indexBlog);
        //获取所有回复数据
        PageInfo page=postService.selectForward(post_id,pageNum,5);
        List<Post> arrayList=page.getList();
        ArrayList<IndexBlog> arrayList1=new ArrayList<>();
        for(Post post1 : arrayList){
            IndexBlog indexBlog1=new IndexBlog();
            indexBlog1.setPost(post1);
            indexBlog1.setAvatar(userService.selectAvatarFromId(post1.getUser_id()));
            arrayList1.add(indexBlog1);
        }
        result.putAll(PageUtil.setPageInfo(page,result));
        result.put("datalists",arrayList1);
        result.put("url","/microblog/forward/list");
        return "/microblog/forward/list";
    }

    @RequestMapping({"/getForward"})
    public String getForward(int pid, Map<String,Object> result){
        List<Post> posts=postService.selectForwardInfo(pid);
        ArrayList<IndexBlog> indexBlogs=new ArrayList<>();
        for(Post post : posts){
            IndexBlog indexBlog=new IndexBlog();
            indexBlog.setPost(post);
            User user=userService.selectId(post.getUser_id());
            indexBlog.setAvatar(user.getAvatar());
            indexBlogs.add(indexBlog);
        }
        int total=postService.getTotalForwardNum(pid);
        result.put("datalists",indexBlogs);
        result.put("total",total);
        return "/microblog/forward/forward";
    }
}
