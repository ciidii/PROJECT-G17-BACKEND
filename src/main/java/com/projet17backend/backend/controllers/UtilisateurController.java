package com.projet17backend.backend.controllers;

import com.projet17backend.backend.dto.*;
import com.projet17backend.backend.security.JwtService;
import com.projet17backend.backend.services.Impl.UtilisateurServiceImpl;
import com.projet17backend.backend.services.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "utilisateurs")
@Validated
@EnableMethodSecurity(prePostEnabled = true)
public class UtilisateurController {
    private final UtilisateurService utilisateurService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UtilisateurController(UtilisateurServiceImpl utilisateurService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.utilisateurService = utilisateurService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("ajouter")
    public void ajouter(@Valid @RequestBody UtilisateurDTO utilisateurDTO) {
        this.utilisateurService.ajouter(utilisateurDTO);
    }

    @GetMapping("/tous")
    public List<UtilisateurDTO> utilisateurs() {
        return this.utilisateurService.utilisateurs();
    }

    @GetMapping(path = "{id}")
    public UtilisateurDTO utilisateur(@PathVariable Long id) {
        return this.utilisateurService.utilisateur(id);
    }

    @PutMapping(path = "{id}")
    public void modifierMesInfos(@PathVariable("id") Long id, @Valid @RequestBody UtilisateurDTO utilisateurDTO) {
        this.utilisateurService.modifierMesInfos(id, utilisateurDTO);
    }

    @PutMapping("/modifierMotDePasse")
    public UtilisateurDTO modifierMotDePasse(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        return this.utilisateurService.modifierMotDePasse(changePasswordDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping
    public Boolean verify(@RequestParam("utilisateur") Long utilisateur, @RequestParam("token") String token) {
        return utilisateurService.verifyToken(utilisateur, token);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-utilisateur-by-identifier")
    public UtilisateurDTO getUtilisateurByIdentifier(@RequestParam("identifier") String identifier) {
        return utilisateurService.getUtilisateurByIdentifier(identifier);
    }

    @PostMapping(path = "connexion")
    public Map<String, String> connexion(@RequestBody InfoConnexionDTO infoConnexionDTO) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        infoConnexionDTO.getIdentifiant(),
                        infoConnexionDTO.getMotDePasse()
                )
        );

        if (authenticate.isAuthenticated()) {
            return this.jwtService.generate(infoConnexionDTO.getIdentifiant());
        }
        return null;
    }

    @PutMapping("/bloquer")
    public UtilisateurBoquerDTO bloquerUtilisateur(@RequestBody InfosPourBloquerUtilisateur infosPourBloquerUtilisateur) {
        return this.utilisateurService.bloquerUtilisateur(infosPourBloquerUtilisateur);
    }
}
