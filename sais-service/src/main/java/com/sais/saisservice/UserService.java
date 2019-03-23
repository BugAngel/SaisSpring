package com.sais.saisservice;

import com.sais.saisentity.User;
import com.sais.saismapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    public User selectId(String id){
        return userMapper.selectId(id);
    }

    public ArrayList<User> selectAll(){
        return userMapper.selectAll();
    }

    public int delete(String id){
        return userMapper.delete(id);
    }

    public int delAll(ArrayList<String> list){
        return userMapper.delAll(list);
    }

    public ArrayList<User> lists(){
        return userMapper.lists();
    }

    public ArrayList<User> listsLike(String keyword){
        return userMapper.listsLike(keyword);
    }

    public int updateComment(String account,String comment){
        return userMapper.updateComment(account,comment);
    }
}
