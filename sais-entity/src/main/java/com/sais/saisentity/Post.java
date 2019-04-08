package com.sais.saisentity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 帖子实体类
 */
@Data
public class Post {
    private Integer id;
    String content=""; //帖子内容
    Timestamp addtime; //添加时间
    String nickname; //昵称
    int user_id; //发帖人ID
    int pid=0; //父级帖子ID
    int post_type=0; //帖子类型 0发布 1评论 2转发
    int parent_user_id=0; //父级发帖用户ID
    int forward_num=0; //转发数
    int comment_num=0; //评论数
    int praise_num=0; //点赞数
}
