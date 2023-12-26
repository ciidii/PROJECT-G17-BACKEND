package com.projet17backend.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class VenteDTO {
    private Long idVente;
    private Long idUtilisateur;
    private LocalDate dateDeVente;

    private float totalVente;

    private List<DetailVenteDTO> detailsVente;

    public VenteDTO(Long idVente, Long idUtilisateur, LocalDate dateDeVente, float totalVente, List<DetailVenteDTO> detailsVente) {
        this.idVente = idVente;
        this.idUtilisateur = idUtilisateur;
        this.dateDeVente = dateDeVente;
        this.totalVente = totalVente;
        this.detailsVente = detailsVente;
    }

    public VenteDTO() {
    }

    public Long getIdVente() {
        return idVente;
    }

    public void setIdVente(Long idVente) {
        this.idVente = idVente;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public List<DetailVenteDTO> getDetailsVente() {
        return detailsVente;
    }

    public void setDetailsVente(List<DetailVenteDTO> detailsVente) {
        this.detailsVente = detailsVente;
    }

    public LocalDate getDateDeVente() {
        return dateDeVente;
    }

    public void setDateDeVente(LocalDate dateDeVente) {
        this.dateDeVente = dateDeVente;
    }

    public float getTotalVente() {
        return totalVente;
    }

    public void setTotalVente(float totalVente) {
        this.totalVente = totalVente;
    }
}
