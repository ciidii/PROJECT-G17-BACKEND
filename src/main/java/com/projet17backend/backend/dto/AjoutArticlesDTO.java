package com.projet17backend.backend.dto;

public class AjoutArticlesDTO {
    private String nomArticle;
    private String descriptionArticle;
    private Long qttStock;
    private Long idCategorie;

    public AjoutArticlesDTO(String nomArticle, String descriptionArticle, Long qttStock, Long idCategorie) {
        this.nomArticle = nomArticle;
        this.descriptionArticle = descriptionArticle;
        this.qttStock = qttStock;
        this.idCategorie = idCategorie;
    }

    public AjoutArticlesDTO() {
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

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }
}
