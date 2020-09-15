package com.hrsolutionsystem.hrss.security.config;

import com.hrsolutionsystem.hrss.security.interceptor.RecruiterServiceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RecruiterServiceInterceptor())
                .addPathPatterns("/v1/recruiter")
                .excludePathPatterns("/register/");
    }
}
