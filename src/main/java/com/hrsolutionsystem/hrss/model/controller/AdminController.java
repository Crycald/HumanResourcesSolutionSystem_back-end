package com.hrsolutionsystem.hrss.model.controller;

import com.hrsolutionsystem.hrss.model.service.RecruitersService;
import com.hrsolutionsystem.hrss.model.service.email.registration.EmailRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/v1/admin")
@CrossOrigin("*")
public class AdminController {
    private RecruitersService service;
    private EmailRegistrationService emailRegistrationService;

    @Autowired
    public AdminController(RecruitersService service, EmailRegistrationService emailRegistrationService) {
        this.service = service;
        this.emailRegistrationService = emailRegistrationService;
    }

    @RequestMapping(value = "/send")
    public void send(@RequestBody String recipient) throws MalformedURLException {
        service.sendInvite(recipient);
    }
}
