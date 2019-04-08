package com.sais.saisentity;

import lombok.Data;

/**
 * 点赞实体类
 */
@Data
public class Praise {
    int id;
    int user_id; //点赞用户ID
    int post_id; //帖子ID
}
