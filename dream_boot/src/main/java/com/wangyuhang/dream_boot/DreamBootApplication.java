package com.wangyuhang.dream_boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@MapperScan("com.wangyuhang.dream_boot.mapper")
public class DreamBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamBootApplication.class, args);
    }

}
