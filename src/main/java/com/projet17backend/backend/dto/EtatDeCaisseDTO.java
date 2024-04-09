package com.projet17backend.backend.dto;

public class EtatDeCaisseDTO {
    private float ecartDeCaisse;
    private float sommeDansLaCaisse;
    private float sommeAttendus;

    private boolean validate;
    private UtilisateurDTO utilisateur;

    public EtatDeCaisseDTO() {
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

    public UtilisateurDTO getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurDTO utilisateur) {
        this.utilisateur = utilisateur;
    }
}
