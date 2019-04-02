package com.sais;

import com.sais.saisweb.admin.AdminLoginInterceptor;
import com.sais.saisweb.microblog.MicroBlogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Component
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${file.UploadAccessPath}")
    private String UploadAccessPath;
    @Value("${file.UploadPath}")
    private String UploadPath;

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
        registry.addResourceHandler(UploadAccessPath).addResourceLocations("file:" + UploadPath);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login/**");

        registry.addInterceptor(microBlogInterceptor)
                .addPathPatterns("/microblog/**")
                .addPathPatterns("/college/college/detail/**")
                .excludePathPatterns("/microblog/login/**")
                .excludePathPatterns("/microblog/register/**");
    }
}

