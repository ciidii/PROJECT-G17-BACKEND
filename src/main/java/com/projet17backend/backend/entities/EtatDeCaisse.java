package com.projet17backend.backend.entities;

import jakarta.persistence.*;

@Entity
public class EtatDeCaisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private float ecartDeCaisse;
    private float sommeDansLaCaisse;
    private float sommeAttendus;
    private boolean validate=false;

    @ManyToOne
    private Utilisateur utilisateur;

    public EtatDeCaisse(Long id, float ecartDeCaisse, float sommeDansLaCaisse, float sommeAttendus, boolean validate, Utilisateur utilisateur) {
        Id = id;
        this.ecartDeCaisse = ecartDeCaisse;
        this.sommeDansLaCaisse = sommeDansLaCaisse;
        this.sommeAttendus = sommeAttendus;
        this.validate = validate;
        this.utilisateur = utilisateur;
    }

    public EtatDeCaisse() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public float getEcartDeCaisse() {
        return ecartDeCaisse;
    }

    public void setEcartDeCaisse(float ecartDeCaisse) {
        this.ecartDeCaisse = ecartDeCaisse;
    }

    public float getSommeDansLaCaisse() {
        return sommeDansLaCaisse;
    }

    public void setSommeDansLaCaisse(float sommeDansLaCaisse) {
        this.sommeDansLaCaisse = sommeDansLaCaisse;
    }

    public float getSommeAttendus() {
        return sommeAttendus;
    }

    public void setSommeAttendus(float sommeAttendus) {
        this.sommeAttendus = sommeAttendus;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
