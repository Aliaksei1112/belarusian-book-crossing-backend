package by.hackaton.bookcrossing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String from, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo("a.maiseyenak@gmail.com");
        message.setSubject(subject);
        message.setText("Message from: " + from + "\n\n" + text);
        emailSender.send(message);
    }
}
