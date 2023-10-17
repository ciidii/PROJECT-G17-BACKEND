package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.configuration.Configuration;
import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.entities.Utilisateur;
import com.projet17backend.backend.mapper.MapUtilisateur;
import com.projet17backend.backend.repos.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UtilisateurService implements com.projet17backend.backend.services.UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    @Override
    public void ajouter(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = MapUtilisateur.mapDtoToUlisateur(utilisateurDTO);
        if (!utilisateur.getEmail().contains("@")) throw new RuntimeException("email invalide");
        if (!utilisateur.getEmail().contains(".")) throw new RuntimeException("email invalide");
        String genIdentifiant = Configuration.genereIdentifiant();
        while (utilisateurRepository.findByIdentifiant(genIdentifiant).isPresent()){
            genIdentifiant = Configuration.genereIdentifiant();
        }
        utilisateur.setIdentifiant(genIdentifiant);
        utilisateur.setMotDePasse(Configuration.genereMotDePass());
        utilisateurRepository.save(utilisateur);
    }

}
