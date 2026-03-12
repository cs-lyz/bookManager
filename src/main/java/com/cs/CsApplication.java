package com.cs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cs.mapper")  // 扫描 mapper 接口
public class CsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CsApplication.class, args);
    }
}