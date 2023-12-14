package com.caoyang.springboot3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@ServletComponentScan("com.caoyang.springboot3.filter")
@ImportAutoConfiguration
@MapperScan("com.caoyang.springboot3.mapper")
@SpringBootApplication(scanBasePackages = {"com.caoyang.springboot3"})
public class Springboot3Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot3Application.class, args);
    }

}
