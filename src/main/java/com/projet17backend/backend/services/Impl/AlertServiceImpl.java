package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.dto.AlertDTO;
import com.projet17backend.backend.entities.Alert;
import com.projet17backend.backend.mapper.MapAlert;
import com.projet17backend.backend.repos.AlertRepository;
import com.projet17backend.backend.services.AlertService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlertServiceImpl implements AlertService {
    private  final MapAlert mapAlert;
    private final AlertRepository alertRepository;

    public AlertServiceImpl(MapAlert mapAlert, AlertRepository alertRepository) {
        this.mapAlert = mapAlert;
        this.alertRepository = alertRepository;
    }

    @Override
    public AlertDTO parametrerAlert(AlertDTO alertDTO) {
        if (alertDTO.getSeuilArticle()!=0){
            Alert alert = this.mapAlert.mapDtoAlert(alertDTO);
            alert  = this.alertRepository.save(alert);
            alertDTO = mapAlert.mapAlertDto(alert);
            return alertDTO;
        }
        throw new RuntimeException("Nombre d'annulation doit être différent de 0");
    }

    @Override
    public List<AlertDTO> afficherAlert() {
        List<Alert> alerts =  this.alertRepository.findAll();
        List<AlertDTO>  alertDTOS = new ArrayList<>();
        alerts.forEach(alert ->{
            if (alert.isEtat())
                    alertDTOS.add(mapAlert.mapAlertDto(alert));
                }
                );
        return alertDTOS;
    }

    @Override
    public AlertDTO modiferAlert(AlertDTO alertDTO) {
        Optional<Alert> alert = this.alertRepository.findById(alertDTO.getId());
        if (alert.isEmpty()){
            return null;
        }
        return parametrerAlert(alertDTO);
    }

    @Override
    public boolean supprimer(AlertDTO alertDTO) {
        Alert alert = this.mapAlert.mapDtoAlert(alertDTO);
        alert.setEtat(false);
        this.alertRepository.save(alert);
        if (!alert.isEtat())
        {
            return true;
        }
        else {
            throw new RuntimeException("La suppression a échoué");
        }
    }
}
