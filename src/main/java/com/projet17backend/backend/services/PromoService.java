package com.projet17backend.backend.services;

import com.projet17backend.backend.entities.Promo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromoService {
    public Promo parametre(Promo promo);
    List<Promo> lesPromos();

    Promo modifier(Promo promo);
}