package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.entities.Confirmation;
import com.projet17backend.backend.entities.ROLE;
import com.projet17backend.backend.entities.Utilisateur;
import com.projet17backend.backend.mapper.MapUtilisateur;
import com.projet17backend.backend.repos.ConfirmationRepository;
import com.projet17backend.backend.repos.UtilisateurRepository;
import com.projet17backend.backend.services.EmailService;
import com.projet17backend.backend.utils.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurServiceImpl implements com.projet17backend.backend.services.UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MapUtilisateur mapUtilisateur;
    private final EmailService emailService;
    private final ConfirmationRepository confirmationRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, BCryptPasswordEncoder passwordEncoder, MapUtilisateur mapUtilisateur, EmailService emailService, ConfirmationRepository confirmationRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapUtilisateur = mapUtilisateur;
        this.emailService = emailService;
        this.confirmationRepository = confirmationRepository;
    }


    @Override
    public void ajouter(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = mapUtilisateur.mapDtoToUlisateur(utilisateurDTO);
        List<ROLE> roles = new ArrayList<>();
        if (!utilisateur.getEmail().contains("@")) throw new RuntimeException("email invalide");
        if (!utilisateur.getEmail().contains(".")) throw new RuntimeException("email invalide");
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent())
            throw new RuntimeException("email existe déja");
        String genIdentifiant = Configuration.genereIdentifiant();
        while (utilisateurRepository.findByIdentifiant(genIdentifiant).isPresent()) {
            genIdentifiant = Configuration.genereIdentifiant();
        }
        if (utilisateurDTO.getRoles().get(0).equals(ROLE.ROLE_UTILISATEUR)) {
            utilisateur.setRoles(this.mapUtilisateur.mapRolesToString(utilisateurDTO.getRoles()));
        } else if (utilisateurDTO.getRoles().get(0).equals(ROLE.ROLE_ADMIN)) {
            roles.add(ROLE.ROLE_UTILISATEUR);
            roles.add(ROLE.ROLE_ADMIN);
            utilisateurDTO.setRoles(roles);
            utilisateur.setRoles(this.mapUtilisateur.mapRolesToString(utilisateurDTO.getRoles()));
        } else if (utilisateurDTO.getRoles().get(0).equals(ROLE.ROLE_FINANCIER)) {
            roles.add(ROLE.ROLE_UTILISATEUR);
            roles.add(ROLE.ROLE_FINANCIER);
            utilisateurDTO.setRoles(roles);
            utilisateur.setRoles(this.mapUtilisateur.mapRolesToString(utilisateurDTO.getRoles()));
        }
        utilisateur.setIdentifiant(genIdentifiant);
        String notEncodedPassWorld = Configuration.genereMotDePass();
        String passEncoded = this.passwordEncoder.encode(notEncodedPassWorld);
        utilisateur.setMotDePasse(passEncoded);
        utilisateurRepository.save(utilisateur);
        Confirmation confirmation = new Confirmation(utilisateur);
         emailService.sendMailMessage(utilisateur.getPrenom() + " " + utilisateur.getNom(), confirmation.getUtilisateur().getIdentifiant(), notEncodedPassWorld, confirmation.getUtilisateur().getEmail(), confirmation.getToken(), confirmation.getUtilisateur().getIdUtilisateur());
        confirmationRepository.save(confirmation);
    }


    @Override
    public List<UtilisateurDTO> utilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll(Sort.by("idUtilisateur"));
        List<UtilisateurDTO> utilisateurDTOS = new ArrayList<>();
        for (Utilisateur utilisateur : utilisateurs) {
            utilisateurDTOS.add(this.mapUtilisateur.mapUtilisateurToDto(utilisateur));
        }
        return utilisateurDTOS;
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

    @Override
    public boolean existParIdUtilisateur(Long id) {
        return utilisateurRepository.existsByIdUtilisateur(id);
    }

    @Override
    public Utilisateur troverUtilisateurAvecSonId(Long id) {
        return utilisateurRepository.findByIdUtilisateur(id);
    }

    @Override
    public Boolean verifyToken(Long idUtilisateur, String token) {
        if (!utilisateurRepository.existsByIdUtilisateur(idUtilisateur)) {
            throw new RuntimeException("Utilisateur introvable");
        }
        if (!confirmationRepository.existsByToken(token)) {
            throw new RuntimeException("Token n'exist pas");
        }
        Utilisateur utilisateur = utilisateurRepository.findByIdUtilisateur(idUtilisateur);
        if (utilisateur != null && !utilisateur.getActivated()) {
            utilisateur.setActivated(true);
            utilisateurRepository.save(utilisateur);
            return true;
        }
        return false;
    }

    @Override
    public Utilisateur trouverParIdentifiant(String identifiant) {
        return this.utilisateurRepository.findByIdentifiant(identifiant).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utilisateurRepository.findByIdentifiant(username).orElseThrow(() -> new UsernameNotFoundException("Informations Incorrectent"));
    }
}