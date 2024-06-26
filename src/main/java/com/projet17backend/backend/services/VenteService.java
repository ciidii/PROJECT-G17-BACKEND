package com.projet17backend.backend.services;

import com.projet17backend.backend.dto.EtatDeCaisseDTO;
import com.projet17backend.backend.dto.InformationFinancierDTO;
import com.projet17backend.backend.dto.MesVentesDto;
import com.projet17backend.backend.dto.VenteDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface        VenteService {
    public VenteDTO ajouter(VenteDTO venteDTO);

   public MesVentesDto trouveVenteUtilisateur(Long utilisateur, LocalDate date);

  public EtatDeCaisseDTO sortirEcartDeCaisse(Long utilisateur, float sommeEnCaisse,Boolean envoyer);

    EtatDeCaisseDTO validateEtatDeCaisse(Long adminOrFinancier, Long etatDeCaisse,Boolean validate);

    List<EtatDeCaisseDTO> afficherTous();

    InformationFinancierDTO suivreInformationFinancier(Long idFinancier, LocalDate dateUtilisateur);
}
