package com.sais.saismapper;

import com.sais.saisentity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserMapper {
    User selectId(String id);
    ArrayList<User> selectAll();
    int delete(String id);
    int delAll(ArrayList<String> list);
    ArrayList<User> lists();
    ArrayList<User> listsLike(String keyword);
    int updateComment(String account,String comment);
}