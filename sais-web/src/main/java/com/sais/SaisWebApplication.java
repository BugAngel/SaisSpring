package com.sais;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;


@MapperScan("com.sais.saismapper") //扫描的mapper
@SpringBootApplication
public class SaisWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaisWebApplication.class, args);
    }

}
