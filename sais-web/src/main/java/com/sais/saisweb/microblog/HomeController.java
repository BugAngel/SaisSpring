package com.sais.saisweb.microblog;

import com.sais.saisentity.*;
import com.sais.saisservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/microblog/home"})
public class HomeController {
    private UserService userService;
    private PostService postService;
    private FriendService friendService;
    private CollectService collectService;
    private PraiseService praiseService;
    private BlogService blogService;
    private AtService atService;

    @Autowired
    public HomeController(UserService userService, PostService postService, FriendService friendService, CollectService collectService, PraiseService praiseService, BlogService blogService,AtService atService){
        this.userService=userService;
        this.postService=postService;
        this.friendService=friendService;
        this.collectService=collectService;
        this.praiseService=praiseService;
        this.blogService = blogService;
        this.atService=atService;
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
            indexBlog=blogService.getForward(indexBlog,post);
            datalists.add(indexBlog);
        }
        result.put("datalists",datalists);
        return "microblog/home/index";
    }

    @RequestMapping({"/friend"})
    public String friend(HttpServletRequest request,Map<String,Object> result){
        User user=(User) request.getSession().getAttribute("user");
        int user_id = user.getId();
        int total = friendService.selectMyFriendsNum(user_id);
        ArrayList<Friend> friends=friendService.selectMyFriends(user_id);
        ArrayList<User> datalists=new ArrayList<>();
        if(friends!=null){
            for(Friend vo : friends){
                User data=userService.selectId(vo.getFriend_id());
                datalists.add(data);
            }
        }
        result.put("total",total);
        result.put("datalists",datalists);
        return "microblog/home/friend";
    }

    @RequestMapping({"/fan"})
    public String fan(HttpServletRequest request,Map<String,Object> result){
        User user=(User) request.getSession().getAttribute("user");
        int user_id = user.getId();
        int total=friendService.selectMyFansNum(user_id);
        ArrayList<Friend> fans=friendService.selectMyFans(user_id);
        ArrayList<User> datalists=new ArrayList<>();
        if(fans!=null){
            for(Friend vo : fans){
                User data=userService.selectId(vo.getUser_id());
                datalists.add(data);
            }
        }
        result.put("total",total);
        result.put("datalists",datalists);
        return "microblog/home/fan";
    }

    @RequestMapping({"/collect"})
    public String collect(HttpServletRequest request,Map<String,Object> result) {
        User user=(User) request.getSession().getAttribute("user");
        int user_id = user.getId();
        List<Collect> collects=collectService.selectMyCollects(user_id);
        ArrayList<IndexBlog> indexBlogs=new ArrayList<>();
        if(collects!=null){
            //获取数据
            for(Collect collect : collects){
                IndexBlog indexBlog=new IndexBlog();
                Post post=postService.selectPostFromId(collect.getPost_id());
                indexBlog.setAvatar(userService.selectAvatarFromId(user_id));
                indexBlog.setCollect(1);
                indexBlog= blogService.setAllCount(indexBlog,post.getId());
                indexBlog= blogService.getForward(indexBlog,post);
                indexBlogs.add(indexBlog);
            }

        }
        result.put("datalists",indexBlogs);
        return "microblog/home/collect";
    }

    @RequestMapping({"/praise"})
    public String praise(HttpServletRequest request,Map<String,Object> result) {
        User user=(User) request.getSession().getAttribute("user");
        int user_id = user.getId();
        int total=praiseService.getMyPraiseNum(user_id);
        List<Praise> praise_lists = praiseService.getMyPraises(user_id);
        ArrayList<IndexBlog> indexBlogs=new ArrayList<>();
        if(praise_lists!=null){
            for(Praise praise : praise_lists){
                IndexBlog indexBlog=new IndexBlog();
                Post post=postService.selectPostFromId(praise.getPost_id());
                indexBlog.setPost(post);
                indexBlog.setAvatar(userService.selectAvatarFromId(user_id));
                indexBlog=blogService.setCollect(indexBlog,user_id,post.getId());
                indexBlog= blogService.setAllCount(indexBlog,post.getId());
                indexBlog= blogService.getForward(indexBlog,post);
                indexBlogs.add(indexBlog);
            }
        }
        result.put("datalists",indexBlogs);
        return "microblog/home/praise";
    }

    @RequestMapping({"/atme"})
    public String atme(HttpServletRequest request,Map<String,Object> result) {
        User user=(User) request.getSession().getAttribute("user");
        int user_id = user.getId();
        List<At> at_lists=atService.getMyAts(user_id);
        ArrayList<IndexBlog> indexBlogs=new ArrayList<>();
        if(at_lists!=null){
            //获取数据
            for(At at:at_lists){
                IndexBlog indexBlog=new IndexBlog();
                Post post=postService.selectPostFromId(at.getPost_id());
                indexBlog.setAvatar(userService.selectAvatarFromId(user_id));
                indexBlog=blogService.setCollect(indexBlog,user_id,post.getId());
                indexBlog= blogService.setAllCount(indexBlog,post.getId());
                indexBlogs.add(indexBlog);
            }
        }
        result.put("datalists",indexBlogs);
        return "microblog/home/atme";
    }

    @RequestMapping({"/message"})
    public String message(HttpServletRequest request,Map<String,Object> result) {
        User user=(User) request.getSession().getAttribute("user");
        int user_id = user.getId();
        ArrayList<IndexBlog> indexBlogs=new ArrayList<>();
        List<Post> message=postService.selectMyMessage(user_id);
        for(Post post:message){
            IndexBlog indexBlog=new IndexBlog();
            indexBlog.setParent(post);
            indexBlog.setPost(postService.selectPostFromId(post.getPid()));
            indexBlog.setAvatar(userService.selectAvatarFromId(post.getUser_id()));
            indexBlogs.add(indexBlog);
        }
        result.put("datalists",indexBlogs);
        return "microblog/home/message";
    }
}
