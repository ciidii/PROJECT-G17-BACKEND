package com.projet17backend.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVente;
    private LocalDate dateDeVente=LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;
    @OneToMany(mappedBy = "vente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetailVente> detailsVente;

    public Vente(LocalDate dateDeVente, Utilisateur utilisateur, List<DetailVente> detailsVente) {
        this.dateDeVente = dateDeVente;
        this.utilisateur = utilisateur;
        this.detailsVente = detailsVente;
    }

    public Vente() {

    }

    public Long getIdVente() {
        return idVente;
    }

    public void setIdVente(Long idVente) {
        this.idVente = idVente;
    }

    public LocalDate getDateDeVente() {
        return dateDeVente;
    }

    public void setDateDeVente(LocalDate dateDeVente) {
        this.dateDeVente = dateDeVente;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<DetailVente> getDetailsVente() {
        return detailsVente;
    }

    public void setDetailsVente(List<DetailVente> detailsVente) {
        this.detailsVente = detailsVente;
    }
}
