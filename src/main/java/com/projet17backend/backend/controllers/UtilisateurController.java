package com.projet17backend.backend.controllers;

import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.services.Impl.UtilisateurServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,path = "utilisateurs")
@Validated
public class UtilisateurController {
    public UtilisateurServiceImpl utilisateurService;

    public UtilisateurController(UtilisateurServiceImpl utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public void ajouter(@Valid @RequestBody UtilisateurDTO utilisateurDTO){
            this.utilisateurService.ajouter(utilisateurDTO);
    }
    @GetMapping
    public List<UtilisateurDTO> utilisateurs(){
        return this.utilisateurService.utilisateurs();
    }
    @GetMapping(path = "{id}")
    public UtilisateurDTO utilisateur(@PathVariable Long id){
        return this.utilisateurService.utilisateur(id);
    }
    @PutMapping(path = "{id}")
    public void modifierMesInfos(@PathVariable("id") Long id,@Valid @RequestBody UtilisateurDTO utilisateurDTO){
        this.utilisateurService.modifierMesInfos(id,utilisateurDTO);
    }
}
