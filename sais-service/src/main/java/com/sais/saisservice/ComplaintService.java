package com.sais.saisservice;

import com.sais.saisentity.Complaint;
import com.sais.saismapper.ComplaintMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ComplaintService {
    private ComplaintMapper complaintMapper;

    @Autowired
    public ComplaintService(ComplaintMapper complaintMapper){
        this.complaintMapper=complaintMapper;
    }

    public ArrayList<Complaint> selectAll(){
        return complaintMapper.selectAll();
    }

    public int delete(String id){
        return complaintMapper.delete(id);
    }

    public int delAll(ArrayList<String> list){
        return complaintMapper.delAll(list);
    }

    public ArrayList<Complaint> lists(){
        return complaintMapper.lists();
    }

    public ArrayList<Complaint> listsLike(String keyword){
        return complaintMapper.listsLike(keyword);
    }
}
