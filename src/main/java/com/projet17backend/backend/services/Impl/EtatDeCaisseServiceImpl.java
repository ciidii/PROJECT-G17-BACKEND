package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.dto.EtatDeCaisseDTO;
import com.projet17backend.backend.entities.EtatDeCaisse;
import com.projet17backend.backend.entities.Utilisateur;
import com.projet17backend.backend.mapper.MapEtatDeCaisse;
import com.projet17backend.backend.repos.EtatDeCaisseRepository;
import com.projet17backend.backend.repos.UtilisateurRepository;
import com.projet17backend.backend.services.EtatDeCaisseService;
import org.springframework.stereotype.Service;

@Service
public class EtatDeCaisseServiceImpl implements EtatDeCaisseService {
    private final MapEtatDeCaisse mapEtatDeCaisse;
    private final EtatDeCaisseRepository etatDeCaisseRepository;
    private final UtilisateurRepository utilisateurRepository;

    public EtatDeCaisseServiceImpl(MapEtatDeCaisse mapEtatDeCaisse, EtatDeCaisseRepository etatDeCaisseRepository, UtilisateurRepository utilisateurRepository) {
        this.mapEtatDeCaisse = mapEtatDeCaisse;
        this.etatDeCaisseRepository = etatDeCaisseRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public EtatDeCaisseDTO ajouter(EtatDeCaisseDTO etatDeCaisseDTO) {
        EtatDeCaisse etatDeCaisse = this.mapEtatDeCaisse.mapDtoToEtatDeCaisse(etatDeCaisseDTO);
        etatDeCaisse = this.etatDeCaisseRepository.save(etatDeCaisse);
        return this.mapEtatDeCaisse.mapEtatDeCaisseToDTO(etatDeCaisse);
    }

    @Override
    public EtatDeCaisse getEtatDeCaisse(Long id) {
        return verifierEtatDeCaisse(id);
    }

    @Override
    public EtatDeCaisseDTO validateEtatDeCaisse(Long adminOrFinancier, Long etatDeCaisse, Boolean validate) {
        Utilisateur utilisateur = this.utilisateurRepository.findByIdUtilisateur(adminOrFinancier);
        EtatDeCaisse etatDeCaisseFromDb = this.getEtatDeCaisse(etatDeCaisse);
        if (!etatDeCaisseFromDb.isValidate()){
            etatDeCaisseFromDb.setValidate(validate);
            etatDeCaisseFromDb.setUtilisateur(utilisateur);
            etatDeCaisseFromDb = this.etatDeCaisseRepository.save(etatDeCaisseFromDb);
           return this.mapEtatDeCaisse.mapEtatDeCaisseToDTO(etatDeCaisseFromDb);
        }
        throw new RuntimeException("Etat de caisse déja valider");
    }

    private EtatDeCaisse verifierEtatDeCaisse(Long id){
       return this.etatDeCaisseRepository.findById(id).orElseThrow();
    }
}
