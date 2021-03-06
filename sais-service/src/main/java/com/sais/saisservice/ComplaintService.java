package com.sais.saisservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sais.saisentity.Complaint;
import com.sais.saismapper.ComplaintMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 投诉操作服务
 */
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

    public PageInfo listsLike(String keyword, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Complaint> list =  complaintMapper.listsLike(keyword);
        return new PageInfo<>(list);
    }

    public int insert(Complaint complaint){
        return complaintMapper.insert(complaint);
    }
}
