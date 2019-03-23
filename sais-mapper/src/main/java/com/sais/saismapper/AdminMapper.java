package com.sais.saismapper;

import com.sais.saisentity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    Admin sel(String account);
    String getPassword(String account);
    int changePassword(String account,String password);
    int updateIP(String account,String loginip);
}
