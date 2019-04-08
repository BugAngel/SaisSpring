package com.sais.saisweb.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sais.saisentity.Complaint;
import com.sais.saisservice.ComplaintService;
import com.sais.saisweb.common.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

/**
 * 投诉列表控制器
 */
@Controller
@RequestMapping("/admin/complain_list")
public class ComplainListController {
    private ComplaintService complaintService;

    @Autowired
    public ComplainListController(ComplaintService complaintService){
        this.complaintService=complaintService;
    }

    /**
     * 列表
     */
    @RequestMapping(value = {"/index","/lists"})
    public String lists(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                        @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                        Map<String,Object> result){
        keyword = keyword.trim();
        PageInfo page = complaintService.listsLike(keyword,pageNum,10);

        result.put("keyword",keyword);
        result.putAll(PageUtil.setPageInfo(page,result));
        result.put("url","/admin/complain_list/lists");//url
        result.put("datalists", page.getList());

        return "/admin/complain_list/lists";
    }

    /**
     * 单选删除
     */
    @ResponseBody
    @RequestMapping(value = {"/delete"})
    public String delete(@RequestParam(value = "id") String id){
        String message;
        int res;
        res = complaintService.delete(id);
        if(res>0){
            message = "删除成功";
        }else{
            message = "删除失败";
        }
        return JSON.toJSONString(message);
    }

    /**
     * 全选删除
     */
    @ResponseBody
    @RequestMapping(value = {"/delAll"})
    public String delAll(@RequestParam(value = "ids[]") ArrayList<String> ids){
        String message;
        if(complaintService.delAll(ids)>0){
            message = "删除成功";
        }else {
            message = "删除失败";
        }
        return JSON.toJSONString(message);
    }
}
