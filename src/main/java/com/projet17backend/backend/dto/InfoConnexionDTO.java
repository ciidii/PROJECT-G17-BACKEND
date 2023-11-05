package com.projet17backend.backend.dto;

import jakarta.validation.constraints.NotNull;

public class InfoConnexionDTO {
    @NotNull(message = "Email est Obligatoire")
    private String identifiant;
    @NotNull(message = "Email est Obligatoire")
    private String motDePasse;

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
