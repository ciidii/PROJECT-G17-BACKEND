package com.projet17backend.backend.dto;

import com.projet17backend.backend.entities.Utilisateur;

import java.time.LocalDateTime;

public class UtilisateurBoquerDTO {
    private String nom;
    private String prenom;
    private String numeroTel;
    private String email;
    private String identifiant;
    LocalDateTime dateDeLevee;

    public UtilisateurBoquerDTO(String nom, String prenom, String numeroTel, String email, String identifiant, LocalDateTime dateDeLevee) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTel = numeroTel;
        this.email = email;
        this.identifiant = identifiant;
        this.dateDeLevee = dateDeLevee;
    }
    public UtilisateurBoquerDTO(UtilisateurDTO  utilisateurDTO, LocalDateTime dateDeLevee){
        this.nom = utilisateurDTO.getNom();
        this.prenom = utilisateurDTO.getPrenom();
        this.numeroTel = utilisateurDTO.getNumeroTel();
        this.email = utilisateurDTO.getEmail();
        this.identifiant = utilisateurDTO.getIdentifiant();
        this.setDateDeLevee(dateDeLevee);
    }

    public UtilisateurBoquerDTO() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public LocalDateTime getDateDeLevee() {
        return dateDeLevee;
    }

    public void setDateDeLevee(LocalDateTime dateDeLevee) {
        this.dateDeLevee = dateDeLevee;
    }
}
