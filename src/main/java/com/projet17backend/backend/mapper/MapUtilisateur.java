package com.projet17backend.backend.mapper;

import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.entities.Utilisateur;

public class MapUtilisateur {
    public static Utilisateur mapDtoToUlisateur(UtilisateurDTO utilisateurDTO){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setNumeroTel(utilisateurDTO.getNumeroTel());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setIdentifiant(utilisateurDTO.getIdentifiant());
        utilisateur.setAdresse(utilisateurDTO.getAdresse());
        return utilisateur;
    }
    /*
    public static UtilisateurDTO mapUtilisateurToDto(Utilisateur utilisateur) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.nom(utilisateur.getNom());
        utilisateurDTO.setPrenom(utilisateur.getPrenom());
        utilisateurDTO.setNumeroTel(utilisateur.getNumeroTel());
        utilisateurDTO.setEmail(utilisateur.getEmail());
        utilisateurDTO.setIdentifiant(utilisateur.getIdentifiant());
        utilisateurDTO.setAdresse(utilisateur.getAdresse());
        return utilisateurDTO;
    }

     */

}
