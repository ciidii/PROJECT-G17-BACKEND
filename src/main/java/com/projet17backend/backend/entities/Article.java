package com.projet17backend.backend.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    private String nomArticle;
    private String descriptionArticle;
    private Long qttStock;
    private boolean estVendable;
    private float prix;
    private Date dateCreation;

    @ManyToOne
    private Categorie categorie;

    public Article(String nomArticle, String descriptionArticle, Long qttStock, boolean estVendable, float prix, Date dateCreation) {
        this.nomArticle = nomArticle;
        this.descriptionArticle = descriptionArticle;
        this.qttStock = qttStock;
        this.estVendable = estVendable;
        this.prix = prix;
        this.dateCreation = dateCreation;
    }

    public Article() {
        super();
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
