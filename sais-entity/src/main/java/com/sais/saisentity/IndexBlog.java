package com.sais.saisentity;

import lombok.Data;

/**
 * 帖子包装实体类
 */
@Data
public class IndexBlog{
    private Post post=null; //帖子
    private String avatar=""; //头像
    private int collect=0; //收藏状态
    private Post parent=null; //父帖子
    private String parent_content="";  //父级内容
    private int forward_count=0; //转发数
    private int comment_count=0; //评论数
    private int praise_count=0; //点赞数
}
