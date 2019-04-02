package com.sais.saisservice;

import com.sais.saisentity.Post;
import com.sais.saismapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private PostMapper postMapper;

    @Autowired
    public PostService(PostMapper postMapper){
        this.postMapper=postMapper;
    }

    public int selectParentUserId(int id){
        return postMapper.selectParentUserId(id);
    }

    public int getLastInsertId(){
        return postMapper.getLastInsertId();
    }

    public int insertBlog(String nickname, String content, Timestamp addtime, int user_id, int pid, int post_type, int parent_user_id, String pictures){
        return postMapper.insertBlog(nickname, content, addtime, user_id, pid, post_type, parent_user_id, pictures);
    }

    public int commentBlog(int pid){
        return postMapper.commentBlog(pid);
    }

    public int forwardBlog(int pid){
        return postMapper.forwardBlog(pid);
    }

    public ArrayList<Post> selectAllBlog(){
        return postMapper.selectAllBlog();
    }

    public int praiseAdd(int post_id){
        return postMapper.praiseAdd(post_id);
    }

    public ArrayList<Post> selectCommentInfo(int pid){
        return postMapper.selectCommentInfo(pid);
    }

    public ArrayList<Post> selectForwardInfo(int pid){
        return postMapper.selectForwardInfo(pid);
    }

    public int getTotalForwardNum(int pid){
        return postMapper.getTotalForwardNum(pid);
    }

    public Post selectPostFromId(int id){
        return postMapper.selectPostFromId(id);
    }

    public ArrayList<Post> selectForward(int pid){
        return postMapper.selectForward(pid);
    }

    public int getTotalBlogAndForwardNum(int user_id){
        return postMapper.getTotalBlogAndForwardNum(user_id);
    }

    public ArrayList<Post> selectBlogAndForward(int user_id){
        return postMapper.selectBlogAndForward(user_id);
    }

    public int getUserForwardNum(int pid){
        return postMapper.getUserForwardNum(pid);
    }

    public int getUserCommentNum(int pid){
        return postMapper.getUserCommentNum(pid);
    }

    public ArrayList<Post> selectUserBlog(int id){
        return postMapper.selectUserBlog(id);
    }

    public List<Post> selectMyMessage(int parent_user_id){
        return postMapper.selectMyMessage(parent_user_id);
    }

    public List<Post> selectLikes(String keyword){
        return postMapper.selectLikes(keyword);
    }

    public List<Post> selectPostFromPid(int pid){
        return postMapper.selectPostFromPid(pid);
    }
}
