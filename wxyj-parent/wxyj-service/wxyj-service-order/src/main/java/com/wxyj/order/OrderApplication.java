package com.wxyj.order;

import entity.FeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.wxyj.order.dao"})
@EnableFeignClients(basePackages = {"com.wxyj.goods.feign"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }

    /**
     * 将Feign调用拦截器注入到容器
     * @return
     */
    @Bean
    public FeignInterceptor feignInterceptor()
    {
        return  new FeignInterceptor();
    }
}