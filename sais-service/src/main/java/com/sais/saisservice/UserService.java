package com.sais.saisservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sais.saisentity.User;
import com.sais.saismapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    public User selectId(int id){
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

    public PageInfo listsLike(String keyword, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<User> list =  userMapper.listsLike(keyword);
        return new PageInfo<>(list);
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

    public int register(User user){
        return userMapper.register(user);
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

    public int selectIdFromAccount(String account){ return  userMapper.selectIdFromAccount(account);}

    public String selectAvatarFromId(int id){
        return userMapper.selectAvatarFromId(id);
    }

    public String selectNicknameFromId(int id){
        return userMapper.selectNicknameFromId(id);
    }

    public int updatePostNum(int id){
        return userMapper.updatePostNum(id);
    }

    public int getLastInsertId(){
        return userMapper.getLastInsertId();
    }

    public int addFollowsNum(int id){
        return userMapper.addFollowsNum(id);
    }

    public int addFansNum(int id){
        return userMapper.addFansNum(id);
    }

    public int subtractFollowsNum(int id){
        return userMapper.subtractFollowsNum(id);
    }

    public int subtractFansNum(int id){
        return userMapper.subtractFansNum(id);
    }
}
