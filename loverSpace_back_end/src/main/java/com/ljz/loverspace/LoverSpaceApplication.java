package com.ljz.loverspace;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableAsync
@MapperScan("com.ljz.loverspace.mapper")
public class LoverSpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoverSpaceApplication.class, args);
    }

}
