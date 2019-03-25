package com.sais;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.sais.saismapper") //扫描的mapper
@SpringBootApplication
public class SaisWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaisWebApplication.class, args);
    }

}
