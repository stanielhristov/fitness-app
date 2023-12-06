package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.EmailDTO;
import com.example.individualprojectstaniel.model.dto.UserDTO;
import com.example.individualprojectstaniel.model.dto.WorkoutLogDTO;
import com.example.individualprojectstaniel.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    private void sendSimpleMessage(String to,  String from, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public EmailDTO createEmail(UserDTO user, WorkoutLogDTO last) {
        return null;
    }

    @Override
    public void send(EmailDTO email) {
        sendSimpleMessage(email.getTo(),email.getFrom(), email.getSubject(), email.getBody());
    }
}
