package com.sais.saisentity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Friend {
    int id;
    int user_id;
    int friend_id;
    int status=0;
    Timestamp addtime;
}
