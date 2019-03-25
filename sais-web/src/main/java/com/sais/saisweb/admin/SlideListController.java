package com.sais.saisweb.admin;

import com.alibaba.fastjson.JSON;
import com.sais.saisentity.Slide;
import com.sais.saisservice.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin/slide_list")
public class SlideListController {
    private SlideService slideService;
    @Value("${file.collegeImageUploadPath}")
    private String collegeImageUploadPath;

    @Autowired
    public SlideListController(SlideService slideService){
        this.slideService=slideService;
    }

    /**
     * 列表
     */
    @RequestMapping(value = {"/index","/lists"})
    public String lists(@RequestParam(value = "keyword",required = false) String keyword, Map<String,Object> result){
        if(keyword!=null){
            keyword = keyword.trim();
        }else {
            keyword="";
        }
        ArrayList<Slide> slides;
        slides = slideService.listsLike(keyword);
        result.put("keyword",keyword);
        result.put("datalists", slides);
        return "/admin/slide_list/lists";
    }

    /**
     * 新增
     */
    @GetMapping(value = {"/add"})
    public String add(){
        return "/admin/slide_list/add";
    }

    /**
     * 新增
     */
    @PostMapping(value = {"/addAction"})
    public String addAction(@RequestParam(value = "picture") MultipartFile picture, @RequestParam(value = "college_e_name") String college_e_name,@RequestParam(value = "introduce") String introduce){
        String fileName = picture.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String filePath = collegeImageUploadPath+df.format(new Date())+"/";
        fileName = DigestUtils.md5DigestAsHex(filePath.getBytes()) + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            picture.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = df.format(new Date())+"/"+fileName;
        Slide slide=new Slide(college_e_name,introduce,filename);
        int result = slideService.addSlide(slide);
        if(result>0){
            return "redirect:/admin/slide_list/lists";
        }else {
            return "redirect:/admin/slide_list/add";
        }
    }

    /**
     * 编辑
     */
    @GetMapping(value = {"/edit"})
    public String edit(@RequestParam(value = "id") String id,Map<String,Object> result){
        Slide slide=slideService.selectId(id);
        result.put("slide",slide);
        return "/admin/slide_list/edit";
    }

    /**
     * 编辑
     */
    @PostMapping(value = {"/editAction"})
    public String editAction(@RequestParam(value = "college_e_name") String college_e_name,@RequestParam(value = "introduce") String introduce){
        int result = slideService.updateIntrocuduce(college_e_name,introduce);
        if(result>0){
            return "redirect:/admin/slide_list/lists";
        }else {
            return "redirect:/admin/slide_list/edit";
        }
    }

    /**
     * 单选删除
     */
    @ResponseBody
    @RequestMapping(value = {"/delete"})
    public String delete(@RequestParam(value = "id") String id){
        String message;
        int res;
        res = slideService.delete(id);
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
        if(slideService.delAll(ids)>0){
            message = "删除成功";
        }else {
            message = "删除失败";
        }
        return JSON.toJSONString(message);
    }
}
