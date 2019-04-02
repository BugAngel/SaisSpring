package com.sais.saismapper;

import com.sais.saisentity.At;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtMapper {
    int insertAt(int user_id,int post_id);
    List<At> getMyAts(int user_id);
}