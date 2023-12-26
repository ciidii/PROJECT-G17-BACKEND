package com.projet17backend.backend.controllers;


import com.projet17backend.backend.dto.EtatDeCaisseDTO;
import com.projet17backend.backend.dto.MesVentesDto;
import com.projet17backend.backend.dto.VenteDTO;
import com.projet17backend.backend.services.VenteService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("ventes")
public class VenteController {
    private final VenteService venteService;

    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @PostMapping("vendre")
    public VenteDTO vendre(@RequestBody() VenteDTO venteDTO){
        return this.venteService.ajouter(venteDTO);
    }
    @GetMapping("suivre/{utilisateur}/{date}")
    public MesVentesDto suivreVente(@PathVariable() Long utilisateur, @PathVariable() LocalDate date){
        return this.venteService.trouveVenteUtilisateur(utilisateur,date);
    }

    @GetMapping("etatDeCaisse/{utilisateur}/{sommeEnCaisse}")
    public EtatDeCaisseDTO demanderEcartDeCaisse(@PathVariable() Long utilisateur, @PathVariable() float sommeEnCaisse){
        return this.venteService.sortirEcartDeCaisse(utilisateur,sommeEnCaisse);
    }
}
