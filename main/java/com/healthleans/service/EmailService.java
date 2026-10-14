package com.healthleans.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendResetPasswordEmail(String toEmail, String resetLink) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("HealthLeans - Reset Your Password");
            helper.setText("""
                <h2>Reset Your Password</h2>
                <p>Click the link below to reset your password:</p>
                <a href="%s" style="background:#3b82f6;color:white;padding:12px 20px;text-decoration:none;border-radius:8px;">Reset Password</a>
                <p>This link will expire in 24 hours.</p>
                <p>If you did not request this, please ignore this email.</p>
                """.formatted(resetLink), true);

            mailSender.send(message);
            System.out.println("✅ Reset email sent to: " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email");
        }
    }
}