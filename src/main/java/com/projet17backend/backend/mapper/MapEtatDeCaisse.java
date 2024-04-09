package com.projet17backend.backend.mapper;

import com.projet17backend.backend.dto.EtatDeCaisseDTO;
import com.projet17backend.backend.entities.EtatDeCaisse;
import org.springframework.stereotype.Component;

@Component
public class MapEtatDeCaisse {
    private final MapUtilisateur mapUtilisateur;

    public MapEtatDeCaisse(MapUtilisateur mapUtilisateur) {
        this.mapUtilisateur = mapUtilisateur;
    }

    public EtatDeCaisse mapDtoToEtatDeCaisse(EtatDeCaisseDTO etatDeCaisseDTO) {
        EtatDeCaisse etatDeCaisse = new EtatDeCaisse();
        etatDeCaisse.setEcartDeCaisse(etatDeCaisseDTO.getEcartDeCaisse());
        etatDeCaisse.setSommeDansLaCaisse(etatDeCaisseDTO.getSommeDansLaCaisse());
        etatDeCaisse.setSommeAttendus(etatDeCaisseDTO.getSommeAttendus());
        return etatDeCaisse;
    }

    public EtatDeCaisseDTO mapEtatDeCaisseToDTO(EtatDeCaisse etatDeCaisse) {
        EtatDeCaisseDTO etatDeCaisseDTO = new EtatDeCaisseDTO();
        etatDeCaisseDTO.setSommeDansLaCaisse(etatDeCaisse.getSommeDansLaCaisse());
        etatDeCaisseDTO.setSommeAttendus(etatDeCaisse.getSommeAttendus());
        etatDeCaisseDTO.setEcartDeCaisse(etatDeCaisse.getEcartDeCaisse());
        etatDeCaisseDTO.setUtilisateur(mapUtilisateur.mapUtilisateurToDto(etatDeCaisse.getUtilisateur()));
        etatDeCaisseDTO.setValidate(etatDeCaisse.isValidate());
        return etatDeCaisseDTO;
    }
}
