package com.sais.saismapper;

import com.sais.saisentity.Admin;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface AdminMapper {
    Admin sel(String account);
    String getPassword(String account);
    int changePassword(String account,String password);
    int updateIP(String account,String loginip);
    int login(String account, String loginip, Timestamp logintime);
}
