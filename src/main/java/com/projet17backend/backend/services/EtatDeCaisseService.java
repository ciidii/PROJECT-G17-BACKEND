package com.projet17backend.backend.services;

import com.projet17backend.backend.dto.EtatDeCaisseDTO;
import com.projet17backend.backend.entities.EtatDeCaisse;
import org.springframework.stereotype.Service;

@Service
public interface EtatDeCaisseService {
    public EtatDeCaisseDTO ajouter(EtatDeCaisseDTO etatDeCaisseDTO);
    public EtatDeCaisse getEtatDeCaisse(Long id);

    EtatDeCaisseDTO validateEtatDeCaisse(Long adminOrFinancier, Long etatDeCaisse, Boolean validate);
}
