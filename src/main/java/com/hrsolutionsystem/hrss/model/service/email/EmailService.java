package com.hrsolutionsystem.hrss.model.service.email;

import com.hrsolutionsystem.hrss.model.domain.dto.EmailHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
    @Value("${gmail.username}")
    private String username="jdp200301";

    @Value("${gmail.password}")
    private String userPassword="kodilla12345";
    private final String mailDomain = "@gmail.com";

    private Logger logger = LoggerFactory.getLogger(EmailService.class);

    private Properties mailClientProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); //TLS

        return properties;
    }

    public void sendMail(EmailHolder holder) {
        Session session = Session.getInstance(mailClientProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, userPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username + mailDomain));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(holder.getRecipient())
            );
            message.setSubject(holder.getSubject());
            message.reply(false);
            message.setText(holder.getContent());

            Transport.send(message);
            logger.info("----------------EMAIL----------------");
            logger.info("Sender: " + username + mailDomain);
            logger.info("Recipient : " + holder.getRecipient());
            logger.info("Subject: " + holder.getSubject());
            logger.info("Content: " + holder.getContent());
            logger.info("!!--------------EMAIL--------------!!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
