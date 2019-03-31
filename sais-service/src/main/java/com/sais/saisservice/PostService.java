package com.sais.saisservice;

import com.sais.saisentity.Post;
import com.sais.saismapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

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

    public int insertBlog(String account, String content, Timestamp addtime, int user_id, int pid, int post_type, int parent_user_id, String pictures){
        return postMapper.insertBlog(account, content, addtime, user_id, pid, post_type, parent_user_id, pictures);
    }

    public int postBlog(int user_id){
        return postMapper.postBlog(user_id);
    }

    public int commentBlog(int pid){
        return postMapper.commentBlog(pid);
    }

    public int forwardBlog(int user_id,int pid){
        return postMapper.forwardBlog(user_id,pid);
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
}
