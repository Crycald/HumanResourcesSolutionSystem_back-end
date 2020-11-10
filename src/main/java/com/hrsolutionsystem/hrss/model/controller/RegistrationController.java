package com.hrsolutionsystem.hrss.model.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/v1/register")
@CrossOrigin("*")
public class RegistrationController {
    private final static String FRONT_API = "http://localhost:8090";

    @GetMapping(value = "/redirect")
    public void register(HttpServletResponse response) throws IOException {
        response.sendRedirect(FRONT_API  + "/register");
    }
}
