package com.leo.electricitysystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.leo.electricitysystem.mapper")
public class ElectricitySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectricitySystemApplication.class, args);
    }

}
