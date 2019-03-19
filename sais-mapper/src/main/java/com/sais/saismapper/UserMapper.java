package com.sais.saismapper;

import com.sais.saisentity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User Sel(int id);
}