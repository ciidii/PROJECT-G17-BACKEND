package com.projet17backend.backend.dto;

import com.projet17backend.backend.entities.ROLE;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UtilisateurDTO {
    private Long idUtilisateur;
    @NotNull(message = "Nom est Obligatoire")
    private String nom;
    @NotNull(message = "Prenom est Obligatoire")
    private String prenom;
    @NotNull
    private String numeroTel;
    @Email(message = "Email invalide")
    @NotNull(message = "Email est Obligatoire")
    private String email;
    private String identifiant;
    private String motDePasse;
    @NotNull(message = "l'adresse est Obligatoire")
    private String adresse;
    @NotNull(message = "Preciser le rôle")
    private List<ROLE> roles;
    private boolean activated;
    private boolean premierConnexion;
    private boolean estBloquer;

    public UtilisateurDTO(Long idUtilisateur, String nom, String prenom, String numeroTel, String email, String identifiant, String motDePasse, String adresse, List<ROLE> roles, boolean activated, boolean premierConnexion, boolean estBloquer) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTel = numeroTel;
        this.email = email;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.adresse = adresse;
        this.roles = roles;
        this.activated = activated;
        this.premierConnexion = premierConnexion;
        this.estBloquer = estBloquer;
    }

    public UtilisateurDTO() {
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<ROLE> getRoles() {
        return roles;
    }

    public void setRoles(List<ROLE> roles) {
        this.roles = roles;
    }

    public boolean isEstBloquer() {
        return estBloquer;
    }

    public void setEstBloquer(boolean estBloquer) {
        this.estBloquer = estBloquer;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isPremierConnexion() {
        return premierConnexion;
    }

    public void setPremierConnexion(boolean premierConnexion) {
        this.premierConnexion = premierConnexion;
    }
}
