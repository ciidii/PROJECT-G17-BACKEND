package com.projet17backend.backend.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class AgentPrix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Utilisateur utilisateur;

    @OneToOne
    private Article article;

    private Date date = new Date();

    public AgentPrix(Utilisateur utilisateur, Article article) {
        this.utilisateur = utilisateur;
        this.article = article;
    }

    public AgentPrix() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
