package com.projet17backend.backend.controllers;

import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.services.Impl.UtilisateurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,path = "utilisateurs")
@AllArgsConstructor
@Validated
public class UtilisateurController {
    public UtilisateurService utilisateurService;

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
}
