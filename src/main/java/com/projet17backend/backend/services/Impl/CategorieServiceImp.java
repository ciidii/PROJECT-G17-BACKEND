package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.dto.CategorieDTO;
import com.projet17backend.backend.entities.Categorie;
import com.projet17backend.backend.mapper.MapCategorie;
import com.projet17backend.backend.repos.CategorieRepository;
import com.projet17backend.backend.services.CategorieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImp implements CategorieService {
    private final CategorieRepository categorieRepository;
    private final MapCategorie mapCategorie;

    public CategorieServiceImp(CategorieRepository categorieRepository, MapCategorie mapCategorie) {
        this.categorieRepository = categorieRepository;
        this.mapCategorie = mapCategorie;
    }

    @Override
    public List<Categorie> categories() {
        return this.categorieRepository.findAll();
    }

    @Override
    public CategorieDTO ajouter(CategorieDTO categorieDTO) {
        Categorie categorie = this.mapCategorie.mapDTOToCategorie(categorieDTO);
        return this.mapCategorie.mapCategorieToDTO(this.categorieRepository.save(categorie));
    }
}
