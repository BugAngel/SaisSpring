package com.sais.saismapper;

import com.sais.saisentity.Friend;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;

@Repository
public interface FriendMapper {
    Friend selectFriend(int user_id, int friend_id);
    int insertFriend(int user_id, int friend_id, int status, Timestamp addtime);
    int updateFriend(int id, int status,Timestamp addtime);
    Integer selectFriendStatus(int user_id, int friend_id);
    ArrayList<Friend> selectMyFriends(int user_id);
    int selectMyFriendsNum(int user_id);
    ArrayList<Friend> selectMyFans(int friend_id);
    int selectMyFansNum(int friend_id);
}
