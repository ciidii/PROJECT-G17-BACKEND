    package com.projet17backend.backend.controllers;

    import com.projet17backend.backend.entities.Promo;
    import com.projet17backend.backend.services.PromoService;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("promotion")
    public class PromoController {
        private final PromoService promoService;

        public PromoController(PromoService promoService) {
            this.promoService = promoService;
        }

        @PostMapping
        public Promo parametrer(@RequestBody Promo promo){
            return this.promoService.parametre(promo);
        }

        @GetMapping
        private List<Promo> promos(){
             return this.promoService.lesPromos();
        }
    }