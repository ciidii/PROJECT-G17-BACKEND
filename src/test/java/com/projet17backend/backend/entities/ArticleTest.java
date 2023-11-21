package com.projet17backend.backend.entities;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleTest {

    @Test
    public void testGettersAndSetters() {
        Article article = new Article();
        article.setNomArticle("TestNom");
        article.setDescriptionArticle("TestDescription");
        article.setQttStock(10L);
        article.setEstVendable(true);
        article.setPrix(50.0f);
        article.setDateCreation(new Date());

        assertEquals("TestNom", article.getNomArticle());
        assertEquals("TestDescription", article.getDescriptionArticle());
        assertEquals(10L, article.getQttStock());
        assertTrue(article.isEstVendable());
        assertEquals(50.0f, article.getPrix());
    }

    @Test
    public void testToString() {
        Article article = new Article();
        assertNotNull(article.toString());
    }

    @Test
    public void testCategorie() {
        Article article = new Article();
        Categorie categorie = new Categorie();
        article.setCategorie(categorie);

        assertNotNull(article.getCategorie());
        assertEquals(categorie, article.getCategorie());
    }

}