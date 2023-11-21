package com.projet17backend.backend.dto;

import jakarta.validation.constraints.NotNull;

public class CategorieDTO {
    private Long idCategorie;
    @NotNull(message = "Le nom du categorie est obligatoire")
    private String nomCat;

    private String descriptionCat;

    public CategorieDTO(Long idCategorie, String nomCat, String descriptionCat) {
        this.idCategorie = idCategorie;
        this.nomCat = nomCat;
        this.descriptionCat = descriptionCat;
    }

    public CategorieDTO() {
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
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
}
