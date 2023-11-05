package com.projet17backend.backend.services;

public interface EmailService {
    void sendMailMessage(String name,String Identifiant,String motDePass,String to, String token,Long idUtilisateur);
}
