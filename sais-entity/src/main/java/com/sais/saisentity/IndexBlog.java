package com.sais.saisentity;

import lombok.Data;

@Data
public class IndexBlog{
    private Post post=null;
    private String avatar="";
    private String nickname="";
    private int collect=0;
    private Post parent=null;
    private String parent_content="";
    private int forward_count=0;
    private int comment_count=0;
    private int praise_count=0;
}
