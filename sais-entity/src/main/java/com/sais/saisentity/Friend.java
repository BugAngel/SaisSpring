package com.sais.saisentity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 朋友实体类
 */
@Data
public class Friend {
    int id;
    int user_id; //用户ID
    int friend_id; //朋友ID
    int status=0; //状态
    Timestamp addtime; //添加时间
}
