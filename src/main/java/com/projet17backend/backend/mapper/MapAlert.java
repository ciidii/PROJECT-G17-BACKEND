package com.projet17backend.backend.mapper;

import com.projet17backend.backend.dto.AlertDTO;
import com.projet17backend.backend.entities.Alert;
import org.springframework.stereotype.Component;

@Component
public class MapAlert {
   public Alert mapDtoAlert(AlertDTO alertDTO){
        Alert alert = new Alert();
        alert.setId(alertDTO.getId());
        alert.setType_alert(alertDTO.getType_alert());
        alert.setEstDeclencher(alertDTO.isEstDeclencher());
        alert.setFinPromo(alertDTO.getFinPromo());
        alert.setLibelle(alertDTO.getLibelle());
        alert.setNombreAnulation(alertDTO.getNombreAnulation());
        alert.setSeuilArticle(alertDTO.getSeuilArticle());
        return alert;
    }
    public AlertDTO mapAlertDto(Alert alert) {
        AlertDTO alertDTO = new AlertDTO();
        alertDTO.setId(alert.getId());
        alertDTO.setType_alert(alert.getType_alert());
        alertDTO.setEstDeclencher(alert.isEstDeclencher());
        alertDTO.setFinPromo(alert.getFinPromo());
        alertDTO.setLibelle(alert.getLibelle());
        alertDTO.setNombreAnulation(alert.getNombreAnulation());
        alertDTO.setSeuilArticle(alert.getSeuilArticle());
        return alertDTO;
    }
}
