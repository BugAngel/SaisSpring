package com.sais.saisentity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Post {
    private Integer id;
    String content="";
    Timestamp addtime;
    String account;
    int user_id;
    int pid=0;
    int post_type=0;
    int parent_user_id=0;
    String pictures="";
    int forward_num=0;
    int comment_num=0;
    int praise_num=0;
}
