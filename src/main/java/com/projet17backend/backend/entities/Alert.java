package com.projet17backend.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Alert implements Serializable {

    private String libelle;
    private int seuilArticle;
    private LocalDate dateAjout = LocalDate.now();
    private LocalDate finPromo;
    private int nombreAnulation;
    private TYPE_ALERT type_alert;
    private boolean estDeclencher=false;
    private boolean etat=true;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    public Alert(String libelle, int seuilArticle, LocalDate dateAjout, LocalDate finPromo, int nombreAnulation, TYPE_ALERT type_alert, boolean estDeclencher, boolean etat) {
        this.libelle = libelle;
        this.seuilArticle = seuilArticle;
        this.dateAjout = dateAjout;
        this.finPromo = finPromo;
        this.nombreAnulation = nombreAnulation;
        this.type_alert = type_alert;
        this.estDeclencher = estDeclencher;
        this.etat = etat;
    }

    public Alert() {
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

    public LocalDate getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDate dateAjout) {
        this.dateAjout = dateAjout;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

}
