package com.sais.saisweb.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sais.saisentity.User;
import com.sais.saisservice.UserService;
import com.sais.saisweb.common.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping({"/admin/user_list","/admin"})
public class UserListController {
    private UserService userService;

    @Autowired
    public UserListController(UserService userService){
        this.userService=userService;
    }

    /**
     * 列表
     */
    @RequestMapping(value = {"/index","/lists","/",""})
    public String lists(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                        @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                        Map<String,Object> result){
        keyword = keyword.trim();
        PageInfo page = userService.listsLike(keyword,pageNum,10);

        result.put("keyword",keyword);
        result.putAll(PageUtil.setPageInfo(page,result));
        result.put("url","/admin/user_list/lists");//url
        result.put("datalists", page.getList());

        return "/admin/user_list/lists";
    }

    /**
     * 编辑
     */
    @GetMapping(value = {"/edit"})
    public String edit(@RequestParam(value = "id") int id,Map<String,Object> result){
        User user=userService.selectId(id);
        result.put("user",user);
        return "/admin/user_list/edit";
    }

    /**
     * 编辑
     */
    @ResponseBody
    @PostMapping(value = {"/editAction"})
    public String editAction(@RequestParam(value = "account") String account,@RequestParam(value = "comment") String comment){
        int result;
        result = userService.updateComment(account,comment);
        HashMap<String, Object> res = new HashMap<String, Object>();
        if(result>0){
            res.put("status", 1);
            res.put("message", "操作成功");
        }else{
            res.put("status", 0);
            res.put("message", "操作失败");
        }
        return JSON.toJSONString(res);
    }

    /**
     * 单选删除
     */
    @ResponseBody
    @RequestMapping(value = {"/delete"})
    public String delete(@RequestParam(value = "id") String id){
        String message;
        int res;
        res = userService.delete(id);
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
        if(userService.delAll(ids)>0){
            message = "删除成功";
        }else {
            message = "删除失败";
        }
        return JSON.toJSONString(message);
    }
}
