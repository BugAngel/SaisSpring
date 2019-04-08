package com.sais.saisweb;

import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * freemarker设置
 */
@Configuration
public class FreemarkerConfig {
    private freemarker.template.Configuration configuration;

    @Autowired
    public FreemarkerConfig(freemarker.template.Configuration configuration){
        this.configuration=configuration;
    }

    /**
     * 设置模板继承功能
     */
    @PostConstruct
    public void setSharedVariable(){
        configuration.setSharedVariable("block", new BlockDirective());
        configuration.setSharedVariable("override", new OverrideDirective());
        configuration.setSharedVariable("extends", new ExtendsDirective());
    }
}