package com.sais.saismapper;

import org.springframework.stereotype.Repository;

@Repository
public interface AtMapper {
    int insertAt(int user_id,int post_id);
}