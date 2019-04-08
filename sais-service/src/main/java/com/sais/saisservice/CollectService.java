package com.sais.saisservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sais.saisentity.Collect;
import com.sais.saismapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 点赞过程操作服务
 */
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

    public Integer selectCollectStatus(int user_id,int post_id){
        return collectMapper.selectCollectStatus(user_id, post_id);
    }

    public PageInfo selectMyCollects(int user_id,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Collect> list = collectMapper.selectMyCollects(user_id);
        return new PageInfo<>(list);
    }
}
