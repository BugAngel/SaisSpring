package com.sais.saismapper;

import com.sais.saisentity.Collect;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CollectMapper {
    Collect selectCollect(int user_id, int post_id);

    int insertCollect(int user_id,int post_id);

    int updateCollect(int user_id,int post_id,int status);

    Integer selectCollectStatus(int user_id,int post_id);

    List<Collect> selectMyCollects(int user_id);
}
