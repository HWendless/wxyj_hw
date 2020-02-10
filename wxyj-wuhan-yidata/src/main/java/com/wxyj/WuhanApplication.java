package com.wxyj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
//@MapperScan(basePackages = {"com.wxyj.wuhan.mapper"})
public class WuhanApplication {
    public static void main(String[] args) {
        SpringApplication.run(WuhanApplication.class);


    }

}
