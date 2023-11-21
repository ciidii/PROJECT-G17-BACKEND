package com.projet17backend.backend.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategorieDTOTest {

    @Test
    public void testGettersAndSetters() {
        CategorieDTO categorieDTO = new CategorieDTO();
        categorieDTO.setNomCat("TestCategory");
        categorieDTO.setDescriptionCat("TestDescription");

        assertEquals("TestCategory", categorieDTO.getNomCat());
        assertEquals("TestDescription", categorieDTO.getDescriptionCat());
    }

    @Test
    public void testIdCategorie() {
        CategorieDTO categorieDTO = new CategorieDTO();
        categorieDTO.setIdCategorie(1L);

        assertEquals(1L, categorieDTO.getIdCategorie());
    }

}
