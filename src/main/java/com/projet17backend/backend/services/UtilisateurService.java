package com.projet17backend.backend.services;

import com.projet17backend.backend.dto.ChangePasswordDTO;
import com.projet17backend.backend.dto.UtilisateurBoquerDTO;
import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.entities.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
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

    UtilisateurBoquerDTO bloquerUtilisateur(Long idAdmin, Long idUtilisateur, LocalDateTime dateDeLeveeAutomatique);

    UtilisateurDTO getUtilisateurByIdentifier(String identifier);

    UtilisateurDTO modifierMotDePasse(ChangePasswordDTO changePasswordDTO);
}
