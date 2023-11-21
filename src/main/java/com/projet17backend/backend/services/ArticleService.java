package com.projet17backend.backend.services;

import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.entities.Categorie;

import java.util.List;

public interface ArticleService {

    Article saveArticle(Article article);
    Article getArticle(Long id);
    List<Article> getAllArticles();
    Article updateArticle(Article article);
    void deleteArticle(Article article);
    void deleteArticleById(Long id);

    List<Article> findByNomArticle(String nom);
    List<Article> findByNomArticleContains(String nom);
    List<Article> findByNomPrix (String nom, Double prix);
    List<Article> findByCategorie (Categorie categorie);
    List<Article> findByCategorieIdCat(Long id);
    List<Article> findByOrderByNomArticleAsc();
    List<Article> trierArticlesNomsPrix ();

    Article parametrerPrixArticle(Long articleId, Long idFancier, Float prix);
}
