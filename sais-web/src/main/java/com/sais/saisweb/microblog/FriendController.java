package com.sais.saisweb.microblog;

import com.alibaba.fastjson.JSON;
import com.sais.saisentity.Friend;
import com.sais.saisentity.IndexBlog;
import com.sais.saisentity.Post;
import com.sais.saisentity.User;
import com.sais.saisservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/microblog/friend")
public class FriendController {
    private PostService postService;
    private UserService userService;
    private CollectService collectService;
    private PraiseService praiseService;
    private FriendService friendService;

    @Autowired
    public FriendController(PostService postService, UserService userService,CollectService collectService,PraiseService praiseService,FriendService friendService){
        this.postService=postService;
        this.userService=userService;
        this.collectService=collectService;
        this.praiseService=praiseService;
        this.friendService=friendService;
    }

    @RequestMapping({"/friend_home","/","/index"})
    public String friend_home(HttpServletRequest request,int friend_id, Map<String,Object>result){
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
        ArrayList<Post> row=postService.selectBlogAndForward(friend_id);

        for(Post post : row){
            IndexBlog indexBlog=new IndexBlog();
            indexBlog.setPost(post);
            indexBlog.setAvatar(friend_info.getAvatar());//头像赋值
            //收藏
            Integer integer=collectService.selectCollectStatus(friend_id,post.getId());
            if(integer==null){
                indexBlog.setCollect(0);
            }else {
                indexBlog.setCollect(integer);
            }

            indexBlog.setForward_count(postService.getUserForwardNum(post.getId()));
            indexBlog.setComment_count(postService.getUserCommentNum(post.getId()));
            indexBlog.setPraise_count(praiseService.getUserPraiseNum(post.getId()));

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

            indexBlogs.add(indexBlog);
        }
        result.put("datalists",indexBlogs);
        return "/microblog/friend/friend_home";
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
