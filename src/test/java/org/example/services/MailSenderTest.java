package org.example.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MailSenderTest {
    @Autowired
    private MailSender mailSender;


    @Test
    public void TestSendEmail() throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("miishaqhyaro@gmail");
        message.setSubject("RegistrationMessage");
        message.setSubject("Subject: Welcome to [Your App Name] – Registration Successful!\n" +
                "\n" +
                "Hi Ikenna,\n" +
                "\n" +
                "Thank you for joining Nextcent! We're excited to have you with us.\n" +
                "\n" +
                "To get started, simply log in to your account and explore our features:\n" +
                "[Login Link]\n" +
                "\n" +
                "If you have any questions or need help, our support team is ready to assist you at [support.Nextcent@gmail.com].\n" +
                "\n" +
                "Best regards,\n" +
                "The Nextcent Team\n" +
                "\n" +
                "Nextcent | 312 Clifford Crescent | +3346784567\n");
        assertThrows(MailException.class, () -> mailSender.send(message));
    }
}
