package com.projet17backend.backend.services;

import com.projet17backend.backend.dto.AlertDTO;

import java.util.List;

public interface AlertService {
    public AlertDTO parametrerAlert(AlertDTO alertDTO);

    List<AlertDTO> afficherAlert();

    AlertDTO modiferAlert(AlertDTO alertDTO);

    boolean supprimer(AlertDTO alertDTO);
}
