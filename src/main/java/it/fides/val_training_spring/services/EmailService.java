package it.fides.val_training_spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
  
  @Autowired
  private JavaMailSender mailSender;

  public void send(String from, String to, String subject, String content) {
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

      helper.setFrom(from);
      helper.setTo(to);
      helper.setSubject(subject);
      mimeMessage.setContent(content, "text/html");
      mailSender.send(mimeMessage);
    } catch (MessagingException e) {
    }
  }
}