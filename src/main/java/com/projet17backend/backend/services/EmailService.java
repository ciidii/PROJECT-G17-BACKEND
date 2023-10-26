package com.projet17backend.backend.services;

public interface EmailService {
    void sendMailMessage(String name,String to, String token,Long idUtilisateur);
}
