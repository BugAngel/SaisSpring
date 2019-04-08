package com.sais;

import com.sais.saisweb.admin.AdminLoginInterceptor;
import com.sais.saisweb.microblog.MicroBlogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * web设置
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${file.uploadAccessPath}")
    private String uploadAccessPath;
    @Value("${file.uploadPath}")
    private String uploadPath;

    private AdminLoginInterceptor adminLoginInterceptor;
    private MicroBlogInterceptor microBlogInterceptor;

    @Autowired
    public WebMvcConfig(AdminLoginInterceptor adminLoginInterceptor,MicroBlogInterceptor microBlogInterceptor){
        this.adminLoginInterceptor=adminLoginInterceptor;
        this.microBlogInterceptor=microBlogInterceptor;
    }
    /**
     * 添加静态资源文件，外部可以直接访问地址
     *
     * @param registry 资源处理程序注册表
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler(uploadAccessPath).addResourceLocations("file:" + uploadPath);
    }

    /**
     * 添加拦截器
     * @param registry 拦截器注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录后台，如果被拦截则只能访问登录界面
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login/**");

        //登录论坛或查看学校详情，被拦截只能登录或注册
        registry.addInterceptor(microBlogInterceptor)
                .addPathPatterns("/microblog/**")
                .addPathPatterns("/college/college/detail/**")
                .excludePathPatterns("/microblog/login/**")
                .excludePathPatterns("/microblog/register/**");
    }
}

