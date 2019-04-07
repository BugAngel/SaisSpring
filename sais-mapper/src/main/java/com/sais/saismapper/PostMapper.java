package com.sais.saismapper;

import com.sais.saisentity.Post;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface PostMapper {
    int selectUserIdFromId(int id);

    int getLastInsertId();

    int insertBlog(String nickname,String content, Timestamp addtime,int user_id,int pid,int post_type,int parent_user_id);

    int commentBlog(int pid);

    int forwardBlog(int pid);

    ArrayList<Post> selectAllBlog();

    int praiseAdd(int post_id);

    ArrayList<Post> selectCommentInfo(int pid);

    ArrayList<Post> selectForwardInfo(int pid);

    int getTotalForwardNum(int pid);

    Post selectPostFromId(int id);

    ArrayList<Post> selectForward(int pid);

    int getTotalBlogAndForwardNum(int user_id);

    ArrayList<Post> selectBlogAndForward(int user_id);

    int getUserForwardNum(int pid);

    int getUserCommentNum(int pid);

    ArrayList<Post> selectUserBlog(int id);

    List<Post> selectPostFromParentUserId(int parent_user_id);

    List<Post> selectLikes(String keyword);

    List<Post> selectPostFromPid(int pid);

    List<Post> selectPostFromPidAndPostType(int pid,int post_type);
}
