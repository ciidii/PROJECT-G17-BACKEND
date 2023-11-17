package com.projet17backend.backend.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;


@Entity
@Component
@Table(name = "utilisateur")
public class Utilisateur implements UserDetails {
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
    private String roles;
    private Boolean activated = false;

    private Boolean estBloquer = true;
    private boolean premierConnexion = true;

    public Utilisateur(Long idUtilisateur, String nom, String prenom, String numeroTel, String email, String identifiant, String motDePasse, String adresse, String roles, Boolean activated, Boolean estBloquer, boolean premierConnexion) {
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
        this.estBloquer = estBloquer;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    public Boolean getEstBloquer() {
        return estBloquer;
    }

    public void setEstBloquer(Boolean estBloquer) {
        this.estBloquer = estBloquer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.roles));
    }

    @Override
    public String getPassword() {
        return this.motDePasse;
    }

    @Override
    public String getUsername() {
        return this.identifiant;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.estBloquer;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.activated;
    }
}
