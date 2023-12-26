package com.projet17backend.backend.dto;

public class DetailVenteDTO {
    private Long idDetailVente;
    private Long venteId;
    private Long articleId;
    private float prixUnitaire;
    private int quantite;


    public DetailVenteDTO(Long idDetailVente, long venteId, Long articleId, float prixUnitaire, int quantite) {
        this.idDetailVente = idDetailVente;
        this.venteId = venteId;
        this.articleId = articleId;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
    }

    public DetailVenteDTO() {
    }

    public Long getIdDetailVente() {
        return idDetailVente;
    }

    public void setIdDetailVente(Long idDetailVente) {
        this.idDetailVente = idDetailVente;
    }

    public Long getVenteId() {
        return venteId;
    }

    public void setVenteId(Long venteId) {
        this.venteId = venteId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
