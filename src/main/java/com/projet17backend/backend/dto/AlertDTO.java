package com.projet17backend.backend.dto;

import com.projet17backend.backend.entities.TYPE_ALERT;

import java.time.LocalDate;

public class AlertDTO {
    private Long id;
    private String libelle;
    private int seuilArticle;
    private LocalDate finPromo;
    private int nombreAnulation;
    private TYPE_ALERT type_alert;
    private boolean estDeclencher=false;
    private boolean etat=true;

    public AlertDTO(Long id, String libelle, int seuilArticle, LocalDate finPromo, int nombreAnulation, TYPE_ALERT type_alert, boolean estDeclencher, boolean etat) {
        this.id = id;
        this.libelle = libelle;
        this.seuilArticle = seuilArticle;
        this.finPromo = finPromo;
        this.nombreAnulation = nombreAnulation;
        this.type_alert = type_alert;
        this.estDeclencher = estDeclencher;
        this.etat = etat;
    }

    public AlertDTO() {
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getSeuilArticle() {
        return seuilArticle;
    }

    public void setSeuilArticle(int seuilArticle) {
        this.seuilArticle = seuilArticle;
    }

    public LocalDate getFinPromo() {
        return finPromo;
    }

    public void setFinPromo(LocalDate finPromo) {
        this.finPromo = finPromo;
    }

    public int getNombreAnulation() {
        return nombreAnulation;
    }

    public void setNombreAnulation(int nombreAnulation) {
        this.nombreAnulation = nombreAnulation;
    }

    public TYPE_ALERT getType_alert() {
        return type_alert;
    }

    public void setType_alert(TYPE_ALERT type_alert) {
        this.type_alert = type_alert;
    }

    public boolean isEstDeclencher() {
        return estDeclencher;
    }

    public void setEstDeclencher(boolean estDeclencher) {
        this.estDeclencher = estDeclencher;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
