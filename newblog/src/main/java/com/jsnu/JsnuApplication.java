package com.jsnu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jsnu.dao")
public class JsnuApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsnuApplication.class, args);
    }

}
