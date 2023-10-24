package com.projet17backend.backend.services;

import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.entities.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    public void ajouter(UtilisateurDTO utilisateurDTO);
    public List<UtilisateurDTO> utilisateurs();
    public UtilisateurDTO utilisateur(Long id);
    public void modifierMesInfos(Long id, UtilisateurDTO utilisateurDTO);
}
