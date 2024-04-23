package com.projet17backend.backend.services;

import com.projet17backend.backend.dto.AjoutArticlesDTO;
import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.entities.Categorie;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {

    Article saveArticle(AjoutArticlesDTO article, Long utilisateurId,List<MultipartFile> images);
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
    Article rendreVendable(Article article, Long idFinancier);

    void uploadImage(Long articleId, Long userID, List<MultipartFile> file);
}
