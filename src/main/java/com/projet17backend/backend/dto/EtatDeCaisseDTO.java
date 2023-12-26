package com.projet17backend.backend.dto;

public class EtatDeCaisseDTO {
    private float ecartDeCaisse;
    private float sommeDansLaCaisse;
    private float sommeAttendus;

    public EtatDeCaisseDTO(float ecartDeCaisse, float sommeDansLaCaisse, float sommeAttendus) {
        this.ecartDeCaisse = ecartDeCaisse;
        this.sommeDansLaCaisse = sommeDansLaCaisse;
        this.sommeAttendus = sommeAttendus;
    }

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
}
