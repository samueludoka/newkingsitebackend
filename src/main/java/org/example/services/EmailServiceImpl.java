package org.example.services;

import org.example.dtos.request.EmailBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(
            EmailBody emailBody) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailBody.to());
        message.setFrom("nextcentss@gmail.com");
        message.setSubject(emailBody.subject());
        message.setText(emailBody.text());
        mailSender.send(message);
    }

    @Override
    public void sendEmail(String email, String passwordResetRequest, String message) {

    }
}

