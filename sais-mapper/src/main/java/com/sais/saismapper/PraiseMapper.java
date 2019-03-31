package com.sais.saismapper;

import com.sais.saisentity.Praise;
import org.springframework.stereotype.Repository;

@Repository
public interface PraiseMapper {
    Praise selectPraise(int user_id, int post_id);

    int insertPraise(int user_id,int post_id);
}
