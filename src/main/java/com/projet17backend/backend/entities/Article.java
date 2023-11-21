package com.projet17backend.backend.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    private String nomArticle;
    private String descriptionArticle;
    private Long qttStock;
    private boolean estVendable;
    private boolean estParametrer=false;
    private float prix;

    private Date dateCreation = new Date();

    @ManyToOne //Plusieur categorie peut avoir la même catégorie
    private Categorie categorie;

    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur  utilisateur;

    @ManyToOne
    private Utilisateur estParameterPar;


    public Article(String nomArticle, String descriptionArticle, Long qttStock, boolean estVendable, boolean estParametrer, float prix, Date dateCreation) {
        this.nomArticle = nomArticle;
        this.descriptionArticle = descriptionArticle;
        this.qttStock = qttStock;
        this.estVendable = estVendable;
        this.estParametrer = estParametrer;
        this.prix = prix;
        this.dateCreation = dateCreation;
    }
    public Article() {
        super();
    }
    public boolean isEstParametrer() {
        return estParametrer;
    }

    public void setEstParametrer(boolean estParametrer) {
        this.estParametrer = estParametrer;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescriptionArticle() {
        return descriptionArticle;
    }

    public void setDescriptionArticle(String descriptionArticle) {
        this.descriptionArticle = descriptionArticle;
    }

    public Long getQttStock() {
        return qttStock;
    }

    public void setQttStock(Long qttStock) {
        this.qttStock = qttStock;
    }

    public boolean isEstVendable() {
        return estVendable;
    }

    public void setEstVendable(boolean estVendable) {
        this.estVendable = estVendable;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Categorie getCategorie() {
        return categorie;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getEstParameterPar() {
        return estParameterPar;
    }

    public void setEstParameterPar(Utilisateur estParameterPar) {
        this.estParameterPar = estParameterPar;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", nomArticle='" + nomArticle + '\'' +
                ", descriptionArticle='" + descriptionArticle + '\'' +
                ", qttStock=" + qttStock +
                ", estVendable=" + estVendable +
                ", prix=" + prix +
                ", dateCreation=" + dateCreation +
                ", categorie=" + categorie +
                '}';
    }
}
