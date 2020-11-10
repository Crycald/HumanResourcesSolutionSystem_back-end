package com.hrsolutionsystem.hrss.model.service.email.registration;

import com.hrsolutionsystem.hrss.model.domain.dto.EmailHolder;
import com.hrsolutionsystem.hrss.model.service.email.EmailService;
import com.hrsolutionsystem.hrss.model.service.jwt.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class EmailRegistrationService {
    private EmailService emailService;
    private JwtGenerator jwtGenerator;
    private EmailHolder emailHolder;
    private String generatedJWT;

    @Autowired
    public EmailRegistrationService(EmailService emailService, JwtGenerator jwtGenerator) {
        this.emailService = emailService;
        this.jwtGenerator = jwtGenerator;
    }

    public void createRegistrationEmail(String recipient) throws MalformedURLException {
        generatedJWT = jwtGenerator.generateJWT(recipient);
        URL link = new URL("http://localhost:8080/v1/register/" + generatedJWT);

        emailHolder = new EmailHolder();
        emailService = new EmailService();
        emailHolder.setRecipient(recipient);
        emailHolder.setSubject("Human Resources Solution System - REGISTRATION");
        emailHolder.setContent("Register here: " + link);

        emailService.sendMail(emailHolder);
    }
}
