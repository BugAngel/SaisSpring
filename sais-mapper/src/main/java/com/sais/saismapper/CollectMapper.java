package com.sais.saismapper;

import com.sais.saisentity.Collect;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectMapper {
    Collect selectCollect(int user_id, int post_id);

    int insertCollect(int user_id,int post_id);

    int updateCollect(int user_id,int post_id,int status);

    Integer selectCollectStatus(int user_id,int post_id);
}
