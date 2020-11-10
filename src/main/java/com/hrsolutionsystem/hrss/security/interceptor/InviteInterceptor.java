package com.hrsolutionsystem.hrss.security.interceptor;

import com.hrsolutionsystem.hrss.model.service.jwt.JwtGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class InviteInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String url = request.getRequestURL().toString();
        String generatedUrl = url.substring(url.lastIndexOf("/")).replace("/", "");

        if (JwtGenerator.isTokenExpired(generatedUrl) == false) {
            JwtGenerator.deleteJWT(generatedUrl);
            response.sendRedirect("/v1/register/redirect");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
