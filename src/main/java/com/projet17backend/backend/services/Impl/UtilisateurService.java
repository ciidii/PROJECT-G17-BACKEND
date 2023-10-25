package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.util.Configuration;
import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.entities.ROLE;
import com.projet17backend.backend.entities.Utilisateur;
import com.projet17backend.backend.mapper.MapUtilisateur;
import com.projet17backend.backend.repos.UtilisateurRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService implements com.projet17backend.backend.services.UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final MapUtilisateur mapUtilisateur;
    private Utilisateur utilisateurFromDB;
    public UtilisateurService(UtilisateurRepository utilisateurRepository, MapUtilisateur mapUtilisateur,Utilisateur utilisateur) {
        this.utilisateurRepository = utilisateurRepository;
        this.mapUtilisateur = mapUtilisateur;
        utilisateurFromDB = utilisateur;
    }

    @Override
    public void ajouter(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = mapUtilisateur.mapDtoToUlisateur(utilisateurDTO);
        if (!utilisateur.getEmail().contains("@")) throw new RuntimeException("email invalide");
        if (!utilisateur.getEmail().contains(".")) throw new RuntimeException("email invalide");
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent())
            throw new RuntimeException("email existe déja");
        String genIdentifiant = Configuration.genereIdentifiant();
        while (utilisateurRepository.findByIdentifiant(genIdentifiant).isPresent()) {
            genIdentifiant = Configuration.genereIdentifiant();
        }
        if (utilisateurDTO.getRole().equals(ROLE.ROLE_UTILISATEUR)) {
            utilisateur.setRole(ROLE.ROLE_UTILISATEUR);
        } else if (utilisateurDTO.getRole().equals(ROLE.ROLE_ADMIN)) {
            utilisateur.setRole(ROLE.ROLE_ADMIN);
        } else if (utilisateurDTO.getRole().equals(ROLE.ROLE_FINANCIER)) {
            utilisateur.setRole(ROLE.ROLE_FINANCIER);
        }
        utilisateur.setIdentifiant(genIdentifiant);
        utilisateur.setMotDePasse(Configuration.genereMotDePass());
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public List<UtilisateurDTO> utilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll(Sort.by("idUtilisateur"));
        return utilisateurs.stream()
                .map(
                        utilisateur ->
                                new UtilisateurDTO(
                                        utilisateur.getIdUtilisateur(),
                                        utilisateur.getNom(),
                                        utilisateur.getPrenom(),
                                        utilisateur.getNumeroTel(),
                                        utilisateur.getEmail(),
                                        utilisateur.getIdentifiant(),
                                        null,
                                        utilisateur.getAdresse(),
                                        utilisateur.getRole(),
                                        utilisateur.isPremierConnexion(),
                                        utilisateur.getActivated()
                                )
                ).toList();
    }

    @Override
    public UtilisateurDTO utilisateur(Long id) {
        if (utilisateurRepository.findByIdUtilisateur(id) == null)
            throw new RuntimeException("Utilisateur n'existe pas");
        else {
            return mapUtilisateur.mapUtilisateurToDto(utilisateurRepository.findByIdUtilisateur(id));
        }
    }

    @Override
    public void modifierMesInfos(Long id, UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateurFromDB = utilisateurRepository.findByIdUtilisateur(id);

        if (utilisateurFromDB == null) {
            throw new RuntimeException("Utilisateur n'existe pas");
        }
        if (!utilisateurFromDB.getIdUtilisateur().equals(id)) {
            throw new RuntimeException("ID de l'utilisateur ne correspond pas");
        }
        utilisateurFromDB.setNom(utilisateurDTO.getNom());
        utilisateurFromDB.setPrenom(utilisateurDTO.getPrenom());
        utilisateurFromDB.setNumeroTel(utilisateurDTO.getNumeroTel());
        utilisateurFromDB.setEmail(utilisateurDTO.getEmail());
        utilisateurFromDB.setAdresse(utilisateurDTO.getAdresse());
        utilisateurRepository.save(utilisateurFromDB);
    }



}