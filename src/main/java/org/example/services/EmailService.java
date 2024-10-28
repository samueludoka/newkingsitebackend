package org.example.services;

import org.example.dtos.request.EmailBody;

public interface EmailService {
    void sendSimpleMessage(EmailBody emailBody);

    void sendEmail(String email, String passwordResetRequest, String message);
}
