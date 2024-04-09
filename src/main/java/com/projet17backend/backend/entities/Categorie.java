package com.projet17backend.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCat;
    private String nomCat;
    private String descriptionCat;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categorie") //Un article ne peut avoir qu'un categorie
    private List<Article> articles;
    public Long getIdCat() {
        return idCat;
    }

    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public String getDescriptionCat() {
        return descriptionCat;
    }

    public void setDescriptionCat(String descriptionCat) {
        this.descriptionCat = descriptionCat;
    }

    public List<Article> getArticles() {
        // l'ensemble des articles  associés à une catégorie
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "idCat=" + idCat +
                ", nomCat='" + nomCat + '\'' +
                ", descriptionCat='" + descriptionCat + '\'' +
                '}';
    }

    @Entity
    public static class Alert {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        private Long id;
        private String libelle;
        private int seuilArticle;
        private LocalDate finPromo;
        private int nombreAnulation;
        private TYPE_ALERT type_alert;
        private boolean estDeclencher;

        public Alert() {
        }

        public Alert(Long id, String libelle, int seuilArticle, LocalDate finPromo, int nombreAnulation, TYPE_ALERT type_alert, boolean estDeclencher) {
            this.id = id;
            this.libelle = libelle;
            this.seuilArticle = seuilArticle;
            this.finPromo = finPromo;
            this.nombreAnulation = nombreAnulation;
            this.type_alert = type_alert;
            this.estDeclencher = estDeclencher;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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
    }
}
