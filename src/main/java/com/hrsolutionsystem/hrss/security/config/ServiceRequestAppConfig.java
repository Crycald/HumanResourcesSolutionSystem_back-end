package com.hrsolutionsystem.hrss.security.config;

import com.hrsolutionsystem.hrss.security.interceptor.InviteInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ServiceRequestAppConfig implements WebMvcConfigurer {

    @Autowired
    InviteInterceptor inviteInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(inviteInterceptor)
                .addPathPatterns("/v1/register/*");
    }
}
