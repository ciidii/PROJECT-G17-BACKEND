package com.projet17backend.backend.controllers;

import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.services.Impl.UtilisateurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,path = "utilisateur")
@AllArgsConstructor
@Validated
public class UtilisateurController {
    public UtilisateurService utilisateurService;

    @PostMapping
    public void ajouter(@Valid @RequestBody UtilisateurDTO utilisateurDTO){
            this.utilisateurService.ajouter(utilisateurDTO);
    }
}
