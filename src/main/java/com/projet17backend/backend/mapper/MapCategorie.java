package com.projet17backend.backend.mapper;

import com.projet17backend.backend.dto.CategorieDTO;
import com.projet17backend.backend.entities.Categorie;
import org.springframework.stereotype.Component;

@Component
public class MapCategorie {
    public CategorieDTO mapCategorieToDTO(Categorie categorie){
        CategorieDTO categorieDTO = new CategorieDTO();
        categorieDTO.setIdCategorie(categorie.getIdCat());
        categorieDTO.setNomCat(categorie.getNomCat());
        categorieDTO.setDescriptionCat(categorie.getDescriptionCat());
        return categorieDTO;
        }
        public Categorie mapDTOToCategorie(CategorieDTO categorieDTO){
            Categorie categorie = new Categorie();
            categorie.setNomCat(categorieDTO.getNomCat());
            categorie.setDescriptionCat(categorieDTO.getDescriptionCat());
            return categorie;
        }
}
