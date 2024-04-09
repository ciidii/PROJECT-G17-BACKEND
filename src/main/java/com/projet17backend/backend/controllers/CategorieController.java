package com.projet17backend.backend.controllers;

import com.projet17backend.backend.dto.CategorieDTO;
import com.projet17backend.backend.entities.Categorie;
import com.projet17backend.backend.services.CategorieService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "categories")
@Valid
public class CategorieController {
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping("/tous")
    public List<Categorie> categories(){
        return this.categorieService.categories();
    }

    @PostMapping()
    public CategorieDTO ajouter(@RequestBody @Valid CategorieDTO categorieDTO){
      return   this.categorieService.ajouter(categorieDTO);
    }
}
