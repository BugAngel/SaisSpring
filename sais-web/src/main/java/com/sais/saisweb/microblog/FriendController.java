package com.sais.saisweb.microblog;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sais.saisentity.Friend;
import com.sais.saisentity.IndexBlog;
import com.sais.saisentity.Post;
import com.sais.saisentity.User;
import com.sais.saisservice.*;
import com.sais.saisweb.common.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 好友界面与操作控制器
 */
@Controller
@RequestMapping("/microblog/friend")
public class FriendController {
    private PostService postService;
    private UserService userService;
    private FriendService friendService;
    private BlogService blogService;

    @Autowired
    public FriendController(PostService postService, UserService userService, FriendService friendService, BlogService blogService){
        this.postService=postService;
        this.userService=userService;
        this.friendService=friendService;
        this.blogService = blogService;
    }

    @RequestMapping({"/home","/","/index"})
    public String friend_home(HttpServletRequest request,int friend_id, Map<String,Object>result,
                              @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        User friend_info = userService.selectId(friend_id);
        result.put("friend_info",friend_info);
        User user=(User)request.getSession().getAttribute("user");
        Integer is_friend=friendService.selectFriendStatus(user.getId(),friend_id);
        if (is_friend==null){
            result.put("is_friend",0);
        }else {
            result.put("is_friend",is_friend);
        }
        ArrayList<IndexBlog> indexBlogs=new ArrayList<>();
        PageInfo page=postService.selectBlogAndForward(friend_id,pageNum,5);
        List<Post> row=page.getList();

        for(Post post : row){
            IndexBlog indexBlog=new IndexBlog();
            indexBlog.setPost(post);
            indexBlog.setAvatar(friend_info.getAvatar());//头像赋值
            indexBlog=blogService.setCollect(indexBlog,friend_id,post.getId());
            indexBlog=blogService.setAllCount(indexBlog,post.getId());
            indexBlog=blogService.getForward(indexBlog,post);
            indexBlogs.add(indexBlog);
        }
        result.putAll(PageUtil.setPageInfo(page,result));
        result.put("datalists",indexBlogs);
        result.put("url","/microblog/friend/home");
        return "/microblog/friend/home";
    }

    @ResponseBody
    @RequestMapping("/follow")
    public String follow(HttpServletRequest request,int friend_id){
        User user=(User)request.getSession().getAttribute("user");
        int user_id   = user.getId();
        Date date = new Date();
        Timestamp addtime = new Timestamp(date.getTime());
        Friend friend=friendService.selectFriend(user_id,friend_id);
        if(friend==null){    //不存在该好友信息
            friendService.insertFriend(user_id,friend_id,1,addtime);
            userService.addFollowsNum(user_id);
            userService.addFansNum(friend_id);
            user.setFollows_num(user.getFollows_num()+1);
            request.getSession().setAttribute("user",user);
            return JSON.toJSONString(1);
        }else{  //存在该好友信息
            if(friend.getStatus() == 0){ //已取消关注该好友的情况，重新关注
                int res=friendService.updateFriend(friend.getId(),1,addtime);
                if(res>0){
                    userService.addFollowsNum(user_id);
                    userService.addFansNum(friend_id);
                    user.setFollows_num(user.getFollows_num()+1);
                    request.getSession().setAttribute("user",user);
                }
                return JSON.toJSONString(1);
            }else{  //已经关注该好友时，取消关注
                int res=friendService.updateFriend(friend.getId(),0,addtime);
                if(res>0){
                    userService.subtractFollowsNum(user_id);
                    userService.subtractFansNum(friend_id);
                    user.setFollows_num(user.getFollows_num()-1);
                    request.getSession().setAttribute("user",user);
                }
                return JSON.toJSONString(1);
            }
        }
    }

}
