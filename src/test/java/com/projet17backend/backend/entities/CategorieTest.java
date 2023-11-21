package com.projet17backend.backend.entities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategorieTest {

    @Test
    public void testGettersAndSetters() {
        Categorie categorie = new Categorie();
        categorie.setNomCat("TestCategory");
        categorie.setDescriptionCat("TestDescription");

        assertEquals("TestCategory", categorie.getNomCat());
        assertEquals("TestDescription", categorie.getDescriptionCat());
    }

    @Test
    public void testToString() {
        Categorie categorie = new Categorie();
        assertNotNull(categorie.toString());
    }

    @Test
    public void testArticles() {
        Categorie categorie = new Categorie();
        Article article1 = new Article();
        Article article2 = new Article();

        categorie.setArticles(List.of(article1, article2));

        assertNotNull(categorie.getArticles());
        assertEquals(2, categorie.getArticles().size());
        assertTrue(categorie.getArticles().contains(article1));
        assertTrue(categorie.getArticles().contains(article2));
    }
}