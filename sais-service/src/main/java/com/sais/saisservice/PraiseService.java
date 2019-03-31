package com.sais.saisservice;

import com.sais.saisentity.Praise;
import com.sais.saismapper.PraiseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PraiseService {
    private PraiseMapper praiseMapper;

    @Autowired
    public PraiseService(PraiseMapper praiseMapper){
        this.praiseMapper=praiseMapper;
    }

    public Praise selectPraise(int user_id, int post_id){
        return praiseMapper.selectPraise(user_id,post_id);
    }

    public int insertPraise(int user_id,int post_id){
        return praiseMapper.insertPraise(user_id,post_id);
    }
}
