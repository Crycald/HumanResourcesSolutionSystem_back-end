package com.hrsolutionsystem.hrss.security.interceptor.config;

import com.hrsolutionsystem.hrss.security.interceptor.RecruiterServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class RecruiterServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    RecruiterServiceInterceptor recruiterServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(recruiterServiceInterceptor);
    }
}
