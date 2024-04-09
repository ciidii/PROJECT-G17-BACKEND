package com.projet17backend.backend.controllers;

import com.projet17backend.backend.dto.AlertDTO;
import com.projet17backend.backend.services.AlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alert")
public class AlertController {
    private  final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }
    @PostMapping("parametrer")
    public AlertDTO parametrerAlert(@RequestBody AlertDTO alertDTO){
        return this.alertService.parametrerAlert(alertDTO);
    }
    @PostMapping("modifer")
    public AlertDTO modifierAler(@RequestBody AlertDTO alertDTO){
        return this.alertService.modiferAlert(alertDTO);
    }
    @GetMapping("afficher")
    public List<AlertDTO> afficherAlert(){
      return   this.alertService.afficherAlert();
    }

    @PostMapping("supprimer")
    boolean supprimer(@RequestBody AlertDTO alertDTO){
        return this.alertService.supprimer(alertDTO);
    }

}
