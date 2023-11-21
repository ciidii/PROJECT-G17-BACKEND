package com.projet17backend.backend.services;

import com.projet17backend.backend.dto.CategorieDTO;
import com.projet17backend.backend.entities.Categorie;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategorieService {
    public List<Categorie> categories();

    CategorieDTO ajouter(CategorieDTO categorieDTO);
}
