package com.sais.saismapper;

import com.sais.saisentity.Praise;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PraiseMapper {
    Praise selectPraise(int user_id, int post_id);

    int insertPraise(int user_id,int post_id);

    int getUserPraiseNum(int post_id);

    int getMyPraiseNum(int user_id);

    List<Praise> getMyPraises(int user_id);
}
