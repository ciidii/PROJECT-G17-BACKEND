package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.services.EmailService;
import com.projet17backend.backend.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import com.projet17backend.backend.services.EmailService;
import org.springframework.beans.factory.annotation.Value;


@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    @Value("${VERIFY_EMAIL_HOST}")
    private String host;
    @Value("${EMAIL_ID}")
    private String expediteur;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMailMessage(String name,String identifiant,String motDePass, String to, String token,Long idUtilisateur) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Activation de votre compte");
            message.setTo(to);
            message.setFrom(expediteur);
            message.setText(EmailUtils.getEmailMessage(name,motDePass,identifiant,host,token, idUtilisateur));
            mailSender.send(message);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }
}
