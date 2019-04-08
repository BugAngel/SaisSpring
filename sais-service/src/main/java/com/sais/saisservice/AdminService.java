package com.sais.saisservice;

import com.sais.saisentity.Admin;
import com.sais.saismapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * admin用户管理操作服务
 */
@Service
public class AdminService {
    private AdminMapper adminMapper;

    @Autowired
    public AdminService(AdminMapper adminMapper){
        this.adminMapper=adminMapper;
    }

    public Admin sel(String account){
        return adminMapper.sel(account);
    }

    public int changePassword(String account,String password){
        return adminMapper.changePassword(account,password);
    }

    public boolean checkPassword(String account,String password){
        return password.equals(adminMapper.getPassword(account));
    }

    public int updateIP(String account,String loginip){
        return adminMapper.updateIP(account,loginip);
    }

    public int login(String account, String loginip, Timestamp logintime){
        return adminMapper.login(account,loginip,logintime);
    }
}
