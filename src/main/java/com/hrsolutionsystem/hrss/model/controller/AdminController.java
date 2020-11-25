package com.hrsolutionsystem.hrss.model.controller;

import com.hrsolutionsystem.hrss.model.service.RecruitersService;
import com.hrsolutionsystem.hrss.model.service.email.registration.EmailRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/v1/admin")
@CrossOrigin("*")
public class AdminController {
    private RecruitersService service;
    private EmailRegistrationService emailRegistrationService;
    private final static String FRONT_API = "http://localhost:8090";

    @Autowired
    public AdminController(RecruitersService service, EmailRegistrationService emailRegistrationService) {
        this.service = service;
        this.emailRegistrationService = emailRegistrationService;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/adminPage")
    public void adminPage(HttpServletResponse response) throws IOException {
        response.sendRedirect(FRONT_API + "/admin");
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/send")
    public void send(@RequestBody String recipient) throws MalformedURLException {
        service.sendInvite(recipient);
    }
}
