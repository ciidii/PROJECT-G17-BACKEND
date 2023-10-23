package com.projet17backend.backend.mapper;

import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.entities.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class MapUtilisateur {
    public  Utilisateur mapDtoToUlisateur(UtilisateurDTO utilisateurDTO){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setNumeroTel(utilisateurDTO.getNumeroTel());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setIdentifiant(utilisateurDTO.getIdentifiant());
        utilisateur.setAdresse(utilisateurDTO.getAdresse());
        return utilisateur;
    }

    public  UtilisateurDTO mapUtilisateurToDto(Utilisateur utilisateur) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setIdUtilisateur(utilisateur.getIdUtilisateur());
        utilisateurDTO.setNom(utilisateur.getNom());
        utilisateurDTO.setPrenom(utilisateur.getPrenom());
        utilisateur.setMotDePasse(null);
        utilisateurDTO.setNumeroTel(utilisateur.getNumeroTel());
        utilisateurDTO.setEmail(utilisateur.getEmail());
        utilisateurDTO.setIdentifiant(utilisateur.getIdentifiant());
        utilisateurDTO.setAdresse(utilisateur.getAdresse());
        utilisateurDTO.setActivated(utilisateur.getActivated());
        utilisateurDTO.setPremierConnexion(utilisateur.isPremierConnexion());
        return utilisateurDTO;
    }



}
