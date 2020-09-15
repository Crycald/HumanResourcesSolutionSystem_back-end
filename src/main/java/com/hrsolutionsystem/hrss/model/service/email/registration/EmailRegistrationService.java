package com.hrsolutionsystem.hrss.model.service.email.registration;

import com.hrsolutionsystem.hrss.model.domain.dto.EmailHolder;
import com.hrsolutionsystem.hrss.model.service.email.EmailService;
import com.hrsolutionsystem.hrss.model.service.url.UrlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class EmailRegistrationService {
    private static EmailHolder emailHolder;
    private static EmailService emailService;
    private static String generatedUrl;

    @Autowired
    public EmailRegistrationService(EmailService emailService) {
        this.emailService = emailService;
    }

    public static void createRegistrationEmail(String recipient) throws MalformedURLException {
        UrlGenerator generator = new UrlGenerator();
        generatedUrl = generator.createUrl();
        URL link = new URL("http://localhost:8080/v1/recruiter/register/" + generatedUrl);

        emailHolder = new EmailHolder();
        emailService = new EmailService();
        emailHolder.setRecipient(recipient);
        emailHolder.setSubject("Human Resources Solution System - REGISTRATION");
        emailHolder.setContent("Register here: " + link);

        emailService.sendMail(emailHolder);
    }

    public static String getGeneratedUrl() {
        return generatedUrl;
    }
}
