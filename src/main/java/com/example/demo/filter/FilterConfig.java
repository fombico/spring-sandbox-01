package com.example.demo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AirFilter> airFilter() {
        FilterRegistrationBean<AirFilter> bean = new FilterRegistrationBean<>(new AirFilter());
        bean.addUrlPatterns("/filter/*");
        return bean;
    }
}
