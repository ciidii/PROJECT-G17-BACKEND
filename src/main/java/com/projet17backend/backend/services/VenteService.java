package com.projet17backend.backend.services;

import com.projet17backend.backend.entities.Vente;
import org.springframework.stereotype.Service;

@Service
public interface VenteService {
    public Vente ajouter(Vente vente);
}
