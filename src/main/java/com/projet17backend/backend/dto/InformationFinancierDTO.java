package com.projet17backend.backend.dto;

import java.time.LocalDate;

public class InformationFinancierDTO {
    private int nombreVentes;
    private float totalVente;
    private int nombreDArticle;
    private LocalDate date;

    public InformationFinancierDTO() {
    }

    public InformationFinancierDTO(int nombreVentes, float totalVente, int nombreDArticle, LocalDate date) {
        this.nombreVentes = nombreVentes;
        this.totalVente = totalVente;
        this.nombreDArticle = nombreDArticle;
        this.date = date;
    }

    public int getNombreVentes() {
        return nombreVentes;
    }

    public void setNombreVentes(int nombreVentes) {
        this.nombreVentes = nombreVentes;
    }

    public float getTotalVente() {
        return totalVente;
    }

    public void setTotalVente(float totalVente) {
        this.totalVente = totalVente;
    }

    public int getNombreDArticle() {
        return nombreDArticle;
    }

    public void setNombreDArticle(int nombreDArticle) {
        this.nombreDArticle = nombreDArticle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
