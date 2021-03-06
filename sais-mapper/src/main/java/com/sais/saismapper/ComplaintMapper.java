package com.sais.saismapper;

import com.sais.saisentity.Complaint;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * sais_complaint表操作接口
 */
@Repository
public interface ComplaintMapper {
    ArrayList<Complaint> selectAll();
    int delete(String id);
    int delAll(ArrayList<String> list);
    ArrayList<Complaint> lists();
    ArrayList<Complaint> listsLike(String keyword);
    int insert(Complaint complaint);
}
