package com.sais.saismapper;

import com.sais.saisentity.Friend;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface FriendMapper {
    Friend selectFriend(int user_id, int friend_id);
    int insertFriend(int user_id, int friend_id, int status, Timestamp addtime);
    int updateFriend(int id, int status,Timestamp addtime);
    Integer selectFriendStatus(int user_id, int friend_id);
}
