package com.sais.saisweb.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
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
    @Value("${file.collegeImagesUploadPath}")
    private String collegeImagesUploadPath;

    @Autowired
    public SlideListController(SlideService slideService){
        this.slideService=slideService;
    }

    /**
     * 列表
     */
    @RequestMapping(value = {"/index","/lists"})
    public String lists(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                        @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                        Map<String,Object> result){
        keyword = keyword.trim();
        PageInfo page = slideService.listsLike(keyword,pageNum,10);

        result.put("keyword",keyword);
        result.put("pageNum",page.getPageNum());//当前页数
        result.put("firstPage",page.getNavigateFirstPage());//第一页
        result.put("lastPage",page.getNavigateLastPage());//最后一页
        result.put("pages",page.getPages());//总页数
        result.put("url","/admin/slide_list/lists");//url
        result.put("datalists", page.getList());

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
    public String addAction(@RequestParam(value = "picture") MultipartFile picture, @RequestParam(value = "college_e_name") String college_e_name){
        String fileName = picture.getOriginalFilename();  // 文件名
        if(fileName==null || fileName.equals("")){
            return "redirect:/admin/slide_list/add";
        }
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String filePath = collegeImagesUploadPath+df.format(new Date())+"/";
        fileName = DigestUtils.md5DigestAsHex(filePath.getBytes()) + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            picture.transferTo(dest);
        } catch (IOException e) {
            return "redirect:/admin/slide_list/add";
        }
        String filename = df.format(new Date())+"/"+fileName;
        Slide slide=new Slide(college_e_name,filename);
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
    public String edit(@RequestParam(value = "id") int id,Map<String,Object> result){
        Slide slide=slideService.selectId(id);
        result.put("slide",slide);
        return "/admin/slide_list/edit";
    }

    /**
     * 编辑
     */
    @PostMapping(value = {"/editAction"})
    public String editAction(@RequestParam(value = "picture") MultipartFile picture,@RequestParam(value = "college_e_name") String college_e_name){
        String fileName = picture.getOriginalFilename();  // 文件名
        if(fileName==null || fileName.equals("")){
            return "redirect:/admin/slide_list/edit";
        }
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String filePath = collegeImagesUploadPath+df.format(new Date())+"/";
        fileName = DigestUtils.md5DigestAsHex(filePath.getBytes()) + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            picture.transferTo(dest);
        } catch (IOException e) {
            return "redirect:/admin/slide_list/edit";
        }
        String filename = df.format(new Date())+"/"+fileName;
        int result = slideService.updatePicture(college_e_name,filename);
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
    public String delete(@RequestParam(value = "id") int id){
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
