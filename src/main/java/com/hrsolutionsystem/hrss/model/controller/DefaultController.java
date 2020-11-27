package com.hrsolutionsystem.hrss.model.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/v1")
@CrossOrigin("*")
public class DefaultController {
    @Value("${hrss.api.endpoint.prod}")
    private String HOMEPAGE_URL;

    @RequestMapping("/default")
    public void defaultAfterLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.isUserInRole("ROLE_ADMIN")) {
            response.sendRedirect(HOMEPAGE_URL + "/admin");
        } else if (request.isUserInRole("ROLE_USER")) {
            response.sendRedirect(HOMEPAGE_URL + "/user");
        }
    }
}
