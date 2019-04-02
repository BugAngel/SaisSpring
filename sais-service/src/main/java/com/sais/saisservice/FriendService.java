package com.sais.saisservice;

import com.sais.saisentity.Friend;
import com.sais.saismapper.FriendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class FriendService {
    private FriendMapper friendMapper;

    @Autowired
    public FriendService(FriendMapper friendMapper){
        this.friendMapper=friendMapper;
    }

    public Friend selectFriend(int user_id, int friend_id){
        return friendMapper.selectFriend(user_id, friend_id);
    }

    public int insertFriend(int user_id, int friend_id, int status, Timestamp addtime){
        return friendMapper.insertFriend(user_id, friend_id, status, addtime);
    }

    public int updateFriend(int id, int status,Timestamp addtime){
        return friendMapper.updateFriend(id, status,addtime);
    }

    public Integer selectFriendStatus(int user_id, int friend_id){
        return friendMapper.selectFriendStatus(user_id, friend_id);
    }

    public ArrayList<Friend> selectMyFriends(int user_id){
        return friendMapper.selectMyFriends(user_id);
    }

    public int selectMyFriendsNum(int user_id){
        return friendMapper.selectMyFriendsNum(user_id);
    }

    public ArrayList<Friend> selectMyFans(int friend_id){
        return friendMapper.selectMyFans(friend_id);
    }

    public int selectMyFansNum(int friend_id){
        return friendMapper.selectMyFansNum(friend_id);
    }
}