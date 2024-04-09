package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.dto.ChangePasswordDTO;
import com.projet17backend.backend.dto.UtilisateurBoquerDTO;
import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.entities.Confirmation;
import com.projet17backend.backend.entities.ROLE;
import com.projet17backend.backend.entities.Utilisateur;
import com.projet17backend.backend.mapper.MapUtilisateur;
import com.projet17backend.backend.repos.ConfirmationRepository;
import com.projet17backend.backend.repos.UtilisateurRepository;
import com.projet17backend.backend.services.EmailService;
import com.projet17backend.backend.utils.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class UtilisateurServiceImpl implements com.projet17backend.backend.services.UtilisateurService {
    @Autowired
    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MapUtilisateur mapUtilisateur;
    private final EmailService emailService;
    private final ConfirmationRepository confirmationRepository;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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
        Utilisateur utilisateurFromDB = getUtilisateur(id);
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

    private Utilisateur getUtilisateur(Long id) {
        Utilisateur utilisateurFromDB = utilisateurRepository.findByIdUtilisateur(id);

        if (utilisateurFromDB == null) {
            throw new RuntimeException("Utilisateur n'existe pas");
        }
        return utilisateurFromDB;
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
    public UtilisateurBoquerDTO bloquerUtilisateur(Long idAdmin, Long idUtilisateur, LocalDateTime dateDeLeveeAutomatique) {
        Utilisateur utilisateur = getUtilisateur(idUtilisateur);
        Utilisateur admin = getUtilisateur(idAdmin);
        if (admin.getRoles().equals(ROLE.ROLE_ADMIN.toString())) {
            bloquerUtilisateur(utilisateur);
            long delay = LocalDateTime.now().until(dateDeLeveeAutomatique, ChronoUnit.MILLIS);

            if (delay > 0) {
                // Planifier la tâche pour la levée automatique du blocage
                scheduler.schedule(() -> deBloquerUtilisateur(utilisateur), delay, TimeUnit.MILLISECONDS);
            }
            return getUtilisateurBoquerDTO(dateDeLeveeAutomatique, utilisateur);
        }
        throw new RuntimeException("Vous n'êtes pas autorisé à effectuer cette opération");
    }

    @Override
    public UtilisateurDTO getUtilisateurByIdentifier(String identifier) {
        return this.mapUtilisateur.mapUtilisateurToDto(this.utilisateurRepository.findByIdentifiant(identifier).orElseThrow());
    }

    @Override
    public UtilisateurDTO modifierMotDePasse(ChangePasswordDTO changePasswordDTO) {
        Utilisateur utilisateur = getUtilisateur(changePasswordDTO.getId());
        String passEncoded = this.passwordEncoder.encode(changePasswordDTO.getPassword());
        utilisateur.setMotDePasse(passEncoded);
        utilisateur.setPremierConnexion(false);
        this.utilisateurRepository.save(utilisateur);
        return this.mapUtilisateur.mapUtilisateurToDto(utilisateur);
    }

    private static UtilisateurBoquerDTO getUtilisateurBoquerDTO(LocalDateTime dateDeLeveeAutomatique, Utilisateur utilisateur) {
        UtilisateurBoquerDTO utilisateurBoquerDTO = new UtilisateurBoquerDTO();
        utilisateurBoquerDTO.setDateDeLevee(dateDeLeveeAutomatique);
        utilisateurBoquerDTO.setEmail(utilisateurBoquerDTO.getEmail());
        utilisateurBoquerDTO.setNom(utilisateurBoquerDTO.getNom());
        utilisateurBoquerDTO.setNumeroTel(utilisateurBoquerDTO.getNumeroTel());
        utilisateurBoquerDTO.setPrenom(utilisateurBoquerDTO.getPrenom());
        utilisateurBoquerDTO.setIdentifiant(utilisateur.getIdentifiant());
        return utilisateurBoquerDTO;
    }

    private void bloquerUtilisateur(Utilisateur utilisateur) {
        utilisateur.setEstBloquer(true);
        this.utilisateurRepository.save(utilisateur);
    }

    private void deBloquerUtilisateur(Utilisateur utilisateur) {
        utilisateur.setEstBloquer(false);
        this.utilisateurRepository.save(utilisateur);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utilisateurRepository.findByIdentifiant(username).orElseThrow(() -> new UsernameNotFoundException("Informations Incorrectent"));
    }
}