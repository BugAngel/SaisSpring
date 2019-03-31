package com.sais.saismapper;

import com.sais.saisentity.Post;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;

@Repository
public interface PostMapper {
    int selectParentUserId(int id);

    int getLastInsertId();

    int insertBlog(String account,String content, Timestamp addtime,int user_id,int pid,int post_type,int parent_user_id,String pictures);

    int postBlog(int user_id);

    int commentBlog(int pid);

    int forwardBlog(int user_id,int pid);

    ArrayList<Post> selectAllBlog();

    int praiseAdd(int post_id);

    ArrayList<Post> selectCommentInfo(int pid);
}
