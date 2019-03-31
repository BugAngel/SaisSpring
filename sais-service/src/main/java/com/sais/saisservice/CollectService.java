package com.sais.saisservice;

import com.sais.saisentity.Collect;
import com.sais.saismapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectService {
    private CollectMapper collectMapper;

    @Autowired
    public CollectService(CollectMapper collectMapper){
        this.collectMapper=collectMapper;
    }

    public Collect selectCollect(int user_id, int post_id){
        return collectMapper.selectCollect(user_id,post_id);
    }

    public int insertCollect(int user_id,int post_id){
        return collectMapper.insertCollect(user_id, post_id);
    }

    public int updateCollect(int user_id,int post_id,int status){
        return collectMapper.updateCollect(user_id, post_id, status);
    }
}
