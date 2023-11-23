package com.projet17backend.backend.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class LogArticles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OPERATION_ARTICLES typeOperation;
    @ManyToOne()
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    private Article article;

    private Date date = new Date();

    public LogArticles(OPERATION_ARTICLES typeOperation, Utilisateur utilisateur, Article article) {
        this.typeOperation = typeOperation;
        this.utilisateur = utilisateur;
        this.article = article;
    }
    public OPERATION_ARTICLES getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(OPERATION_ARTICLES typeOperation) {
        this.typeOperation = typeOperation;
    }
    public LogArticles() {
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
