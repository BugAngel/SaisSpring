package com.sais.saisservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sais.saisentity.Friend;
import com.sais.saismapper.FriendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    public PageInfo selectMyFriends(int user_id,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Friend> list = friendMapper.selectMyFriends(user_id);
        return new PageInfo<>(list);
    }

    public int selectMyFriendsNum(int user_id){
        return friendMapper.selectMyFriendsNum(user_id);
    }

    public PageInfo selectMyFans(int friend_id,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Friend> list = friendMapper.selectMyFans(friend_id);
        return new PageInfo<>(list);
    }

    public int selectMyFansNum(int friend_id){
        return friendMapper.selectMyFansNum(friend_id);
    }
}
