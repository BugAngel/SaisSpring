package com.sais.saisentity;

import lombok.Data;

/**
 * 论坛收藏实体类
 */
@Data
public class Collect {
    int id;
    int user_id; //发帖人ID
    int post_id; //帖子ID
    int status=0; //0为未被收藏，1为已被收藏
}
