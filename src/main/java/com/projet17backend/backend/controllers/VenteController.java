package com.projet17backend.backend.controllers;


import com.projet17backend.backend.dto.EtatDeCaisseDTO;
import com.projet17backend.backend.dto.InformationFinancierDTO;
import com.projet17backend.backend.dto.MesVentesDto;
import com.projet17backend.backend.dto.VenteDTO;
import com.projet17backend.backend.services.VenteService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("ventes")
public class VenteController {
    private final VenteService venteService;

    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @PostMapping("vendre")
    public VenteDTO vendre(@RequestBody() VenteDTO venteDTO) {
        return this.venteService.ajouter(venteDTO);
    }

    @GetMapping("suivre/{utilisateur}/{date}")
    public MesVentesDto suivreVente(@PathVariable() Long utilisateur, @PathVariable() LocalDate date) {
        return this.venteService.trouveVenteUtilisateur(utilisateur, date);
    }

    @GetMapping("etatDeCaisse/{utilisateur}/{sommeEnCaisse}/{envoyer}")
    public EtatDeCaisseDTO demanderEcartDeCaisse(@PathVariable() Long utilisateur, @PathVariable() float sommeEnCaisse, @PathVariable Boolean envoyer) {
        return this.venteService.sortirEcartDeCaisse(utilisateur, sommeEnCaisse, envoyer);
    }

    @GetMapping("etatDeCaisse/validate/{adminOrFinancier}/{etatDeCaisse}/{validate}")
    public EtatDeCaisseDTO validateEtatDeCaisse(@PathVariable Long adminOrFinancier, @PathVariable Long etatDeCaisse, @PathVariable Boolean validate) {
        return this.venteService.validateEtatDeCaisse(adminOrFinancier, etatDeCaisse, validate);
    }

    @GetMapping("etatDeCaisse")
    public List<EtatDeCaisseDTO> afficherToutLesEtatDeCaisse() {
        return this.venteService.afficherTous();
    }

    @GetMapping("inforamationFinancier/{idFinancier}/{dateUtilisateur}")
    public InformationFinancierDTO suivreIformationFinancier(@PathVariable LocalDate dateUtilisateur, @PathVariable Long idFinancier) {
        return this.venteService.suivreInformationFinancier(idFinancier, dateUtilisateur);
    }
}
;