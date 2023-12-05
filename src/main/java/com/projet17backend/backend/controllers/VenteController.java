package com.projet17backend.backend.controllers;


import com.projet17backend.backend.entities.Vente;
import com.projet17backend.backend.services.VenteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ventes")
public class VenteController {
    private final VenteService venteService;

    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @PostMapping("vendre")
    public Vente vendre(@RequestBody() Vente vente){
        return this.venteService.ajouter(vente);
    }
}
