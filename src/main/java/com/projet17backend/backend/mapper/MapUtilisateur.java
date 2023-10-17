package com.projet17backend.backend.mapper;

import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.entities.Utilisateur;

public class MapUtilisateur {
    public static Utilisateur mapDtoToUlisateur(UtilisateurDTO utilisateurDTO){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(utilisateurDTO.nom());
        utilisateur.setPrenom(utilisateurDTO.prenom());
        utilisateur.setNumeroTel(utilisateurDTO.numeroTel());
        utilisateur.setEmail(utilisateurDTO.email());
        utilisateur.setIdentifiant(utilisateurDTO.identifiant());
        utilisateur.setAdresse(utilisateurDTO.adresse());
        return utilisateur;
    }
}
