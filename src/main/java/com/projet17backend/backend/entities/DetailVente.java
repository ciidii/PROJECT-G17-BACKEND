package com.projet17backend.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class DetailVente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetailVente;

    @ManyToOne
    @JoinColumn(name = "id_vente", referencedColumnName = "idVente")
    @JsonIgnore
    private Vente vente;
    @ManyToOne
    private Article article;
    private int quantite;

    public DetailVente(Vente vente, Article article, int quantite) {
        this.vente = vente;
        this.article = article;
        this.quantite = quantite;
    }

    public DetailVente() {

    }

    public Long getIdDetailVente() {
        return idDetailVente;
    }

    public void setIdDetailVente(Long idDetailVente) {
        this.idDetailVente = idDetailVente;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
