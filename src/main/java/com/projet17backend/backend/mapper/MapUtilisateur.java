package com.projet17backend.backend.mapper;

import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.entities.ROLE;
import com.projet17backend.backend.entities.Utilisateur;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MapUtilisateur {
    public Utilisateur mapDtoToUlisateur(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setNumeroTel(utilisateurDTO.getNumeroTel());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setIdentifiant(utilisateurDTO.getIdentifiant());
        utilisateur.setAdresse(utilisateurDTO.getAdresse());
        return utilisateur;
    }

    public UtilisateurDTO mapUtilisateurToDto(Utilisateur utilisateur) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setIdUtilisateur(utilisateur.getIdUtilisateur());
        utilisateurDTO.setNom(utilisateur.getNom());
        utilisateurDTO.setPrenom(utilisateur.getPrenom());
        utilisateurDTO.setIdUtilisateur(utilisateur.getIdUtilisateur());
        utilisateurDTO.setNumeroTel(utilisateur.getNumeroTel());
        utilisateurDTO.setEmail(utilisateur.getEmail());
        utilisateurDTO.setRoles(this.mapStringToRoles(utilisateur.getRoles()));
        utilisateurDTO.setIdentifiant(utilisateur.getIdentifiant());
        utilisateurDTO.setAdresse(utilisateur.getAdresse());
        utilisateurDTO.setActivated(utilisateur.getActivated());
        utilisateurDTO.setPremierConnexion(utilisateur.isPremierConnexion());
        return utilisateurDTO;
    }

    public List<ROLE> mapStringToRoles(String roleAsString) {
        String[] tableauRoles = roleAsString.split(" ");
        return Arrays.stream(tableauRoles)
                .map(ROLE::valueOf)
                .toList();
    }

    public String mapRolesToString(List<ROLE> roles) {
        StringBuilder result = new StringBuilder();
        for (ROLE element : roles) {
            result.append(String.valueOf(element)).append(" ");
        }
        return result.toString();
    }

}
