package com.projet17backend.backend.services;

import com.projet17backend.backend.dto.VenteDTO;
import org.springframework.stereotype.Service;

@Service
public interface VenteService {
    public VenteDTO ajouter(VenteDTO venteDTO);
}
