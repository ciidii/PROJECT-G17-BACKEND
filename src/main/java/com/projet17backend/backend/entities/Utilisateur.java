package com.projet17backend.backend.entities;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;


@Entity
@Component
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    @Column(name = "numero_tel")
    private String numeroTel;
    private String email;
    private String identifiant;
    @Column(name = "not_de_passe")
    private String motDePasse;
    private String adresse;
    @Enumerated(EnumType.STRING)
    private ROLE  role;
    private Boolean activated=false;
    private boolean premierConnexion=true;

    public Utilisateur(Long idUtilisateur, String nom, String prenom, String numeroTel, String email, String identifiant, String motDePasse, String adresse, ROLE role, Boolean activated, boolean premierConnexion) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTel = numeroTel;
        this.email = email;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.adresse = adresse;
        this.role = role;
        this.activated = activated;
        this.premierConnexion = premierConnexion;
    }

    public Utilisateur() {
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

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public boolean isPremierConnexion() {
        return premierConnexion;
    }

    public void setPremierConnexion(boolean premierConnexion) {
        this.premierConnexion = premierConnexion;
    }
}
