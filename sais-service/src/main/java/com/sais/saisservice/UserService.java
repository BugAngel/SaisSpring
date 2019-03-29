package com.sais.saisservice;

import com.sais.saisentity.User;
import com.sais.saismapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    public boolean checkPassword(String account,String password){
        return password.equals(userMapper.getPassword(account));
    }

    public User selectAccount(String account){
        return userMapper.selectAccount(account);
    }

    public int updateIP(String account,String loginip){
        return userMapper.updateIP(account,loginip);
    }

    public int login(String account, String loginip, Timestamp logintime){ //更新登录IP和登录时间
        return userMapper.login(account,loginip,logintime);
    }

    public int register(String account, String password, String nickname,Timestamp addtime){
        return userMapper.register(account,password,nickname,addtime);
    }

    public int updateSetting(User user){
        return userMapper.updateSetting(user);
    }

    public int changePassword(String account,String password){
        return userMapper.changePassword(account,password);
    }

    public int updateAvatar(User user){
        return userMapper.updateAvatar(user);
    }
}
