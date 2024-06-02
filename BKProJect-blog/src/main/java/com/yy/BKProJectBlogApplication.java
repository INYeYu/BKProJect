package com.yy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.yy.mapper")
public class BKProJectBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BKProJectBlogApplication.class,args);
    }
}
