package com.wxyj.wuhan.config;

import com.wxyj.wuhan.filter.OriginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean originFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(originFilter());
        registration.addUrlPatterns("/*");
        registration.setName("OriginFilter");
        registration.setOrder(9);
        return registration;
    }

    @Bean
    public Filter originFilter() {
        return new OriginFilter();
    }
}
