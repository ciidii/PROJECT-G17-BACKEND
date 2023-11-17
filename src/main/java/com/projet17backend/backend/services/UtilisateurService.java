package com.projet17backend.backend.services;

import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.entities.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UtilisateurService extends UserDetailsService {
    public void ajouter(UtilisateurDTO utilisateurDTO);
    public List<UtilisateurDTO> utilisateurs();
    public UtilisateurDTO utilisateur(Long id);
    public void modifierMesInfos(Long id, UtilisateurDTO utilisateurDTO);
    public boolean existParIdUtilisateur(Long id);
    public Utilisateur troverUtilisateurAvecSonId(Long id);
    public Boolean verifyToken(Long idUtilisateur,String token);
    public Utilisateur trouverParIdentifiant(String identifiant);
}
