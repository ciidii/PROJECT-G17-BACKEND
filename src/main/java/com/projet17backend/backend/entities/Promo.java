package com.projet17backend.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "promo")
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    private float tauxDeRemise;

    @ManyToOne(fetch = FetchType.EAGER)
    Utilisateur utilisateur;

    @ManyToMany
    @JoinTable(
            name = "promo_article",
            joinColumns = @JoinColumn(name = "promo_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articles;
    public Promo(Long id, LocalDate dateDebut, LocalDate dateFin, float tauxDeRemise, Utilisateur utilisateur, List<Article> articles) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tauxDeRemise = tauxDeRemise;
        this.utilisateur = utilisateur;
        this.articles = articles;
    }

    public Promo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public float getTauxDeRemise() {
        return tauxDeRemise;
    }

    public void setTauxDeRemise(float tauxDeRemise) {
        this.tauxDeRemise = tauxDeRemise;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}