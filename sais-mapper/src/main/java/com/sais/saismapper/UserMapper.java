package com.sais.saismapper;

import com.sais.saisentity.User;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;

@Repository
public interface UserMapper {
    User selectId(int id);
    User selectAccount(String account);
    ArrayList<User> selectAll();
    int delete(String id);
    int delAll(ArrayList<String> list);
    ArrayList<User> lists();
    ArrayList<User> listsLike(String keyword);
    int updateComment(String account,String comment);
    String getPassword(String account);
    int updateIP(String account,String loginip);
    int login(String account, String loginip, Timestamp logintime);
    int register(User user);
    int updateSetting(User user);
    int changePassword(String account,String password);
    int updateAvatar(User user);
    int selectIdFromAccount(String account);
    String selectAvatarFromId(int id);
    String selectNicknameFromId(int id);
    int updatePostNum(int id);
    int getLastInsertId();
    int addFollowsNum(int id);
    int addFansNum(int id);
    int subtractFollowsNum(int id);
    int subtractFansNum(int id);
}