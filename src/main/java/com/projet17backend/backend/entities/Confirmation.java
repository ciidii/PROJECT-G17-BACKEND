package com.projet17backend.backend.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Confirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String token;
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createdDate;
    @OneToOne
    private Utilisateur utilisateur;

    public Confirmation(Utilisateur utilisateur){
        this.utilisateur = utilisateur;
        this.createdDate = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
    }

    public Confirmation() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
