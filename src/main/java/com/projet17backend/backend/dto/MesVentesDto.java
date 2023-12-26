package com.projet17backend.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class MesVentesDto {
    private Long nbVente;
    private List<VenteDTO> venteDTOS;

    public MesVentesDto(Long nbVente, List<VenteDTO> venteDTOS) {
        this.nbVente = nbVente;
        this.venteDTOS = venteDTOS;
    }

    public MesVentesDto() {
    }

    public Long getNbVente() {
        return nbVente;
    }

    public void setNbVente(Long nbVente) {
        this.nbVente = nbVente;
    }


    public List<VenteDTO> getVenteDTOS() {
        return venteDTOS;
    }

    public void setVenteDTOS(List<VenteDTO> venteDTOS) {
        this.venteDTOS = venteDTOS;
    }
}
