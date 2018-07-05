package com.example.demo.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MagicConfig {

    @Bean
    public MagicBean magicBean1() {
        return new MagicBean("bean 1");
    }

    @Bean
    public MagicBean magicBean2() {
        return new MagicBean("bean 2");
    }

    @Bean
    public MagicBean magicBean3() {
        return new MagicBean("bean 3");
    }
}
