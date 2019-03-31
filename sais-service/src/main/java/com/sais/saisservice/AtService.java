package com.sais.saisservice;

import com.sais.saismapper.AtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtService {
    private AtMapper atMapper;

    @Autowired
    public AtService(AtMapper atMapper){
        this.atMapper=atMapper;
    }

    public int insertAt(int user_id,int post_id){
        return atMapper.insertAt(user_id,post_id);
    }
}
