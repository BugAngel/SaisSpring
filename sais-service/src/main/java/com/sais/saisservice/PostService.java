package com.sais.saisservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sais.saisentity.Post;
import com.sais.saismapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 发帖操作服务
 */
@Service
public class PostService {
    private PostMapper postMapper;

    @Autowired
    public PostService(PostMapper postMapper){
        this.postMapper=postMapper;
    }

    public int selectUserIdFromId(int id){
        return postMapper.selectUserIdFromId(id);
    }

    public int getLastInsertId(){
        return postMapper.getLastInsertId();
    }

    public int insertBlog(String nickname, String content, Timestamp addtime, int user_id, int pid, int post_type, int parent_user_id){
        return postMapper.insertBlog(nickname, content, addtime, user_id, pid, post_type, parent_user_id);
    }

    public int commentBlog(int pid){
        return postMapper.commentBlog(pid);
    }

    public int forwardBlog(int pid){
        return postMapper.forwardBlog(pid);
    }

    public PageInfo selectAllBlog(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Post> list = postMapper.selectAllBlog();
        return new PageInfo<>(list);
    }

    public int praiseAdd(int post_id){
        return postMapper.praiseAdd(post_id);
    }

    public List<Post> selectCommentInfo(int pid){
        return postMapper.selectCommentInfo(pid);
    }

    public List<Post> selectForwardInfo(int pid){
        return postMapper.selectForwardInfo(pid);
    }

    public int getTotalForwardNum(int pid){
        return postMapper.getTotalForwardNum(pid);
    }

    public Post selectPostFromId(int id){
        return postMapper.selectPostFromId(id);
    }

    public PageInfo selectForward(int pid,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Post> list = postMapper.selectForward(pid);
        return new PageInfo<>(list);
    }

    public int getTotalBlogAndForwardNum(int user_id){
        return postMapper.getTotalBlogAndForwardNum(user_id);
    }

    public PageInfo selectBlogAndForward(int user_id,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Post> list = postMapper.selectBlogAndForward(user_id);
        return new PageInfo<>(list);
    }

    public int getUserForwardNum(int pid){
        return postMapper.getUserForwardNum(pid);
    }

    public int getUserCommentNum(int pid){
        return postMapper.getUserCommentNum(pid);
    }

    public PageInfo selectUserBlog(int id,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Post> list = postMapper.selectUserBlog(id);
        return new PageInfo<>(list);
    }

    public PageInfo selectPostFromParentUserId(int parent_user_id,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Post> list =  postMapper.selectPostFromParentUserId(parent_user_id);
        return new PageInfo<>(list);
    }

    public PageInfo selectLikes(String keyword,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Post> list = postMapper.selectLikes(keyword);
        return new PageInfo<>(list);
    }

    public PageInfo selectPostFromPid(int pid,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Post> list = postMapper.selectPostFromPid(pid);
        return new PageInfo<>(list);
    }

    public PageInfo selectComment(int pid,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Post> list = postMapper.selectPostFromPidAndPostType(pid,1);
        return new PageInfo<>(list);
    }
}
