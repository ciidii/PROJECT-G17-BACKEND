package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.entities.Categorie;
import com.projet17backend.backend.entities.Utilisateur;
import com.projet17backend.backend.repos.ArticleRepository;
import com.projet17backend.backend.repos.PrixArticleRepository;
import com.projet17backend.backend.repos.UtilisateurRepository;
import com.projet17backend.backend.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
   private final   ArticleRepository articleRepository;
   private final UtilisateurRepository utilisateurRepository;
   private final ModelMapper modelMapper;
   private final PrixArticleRepository prixArticleRepository;
    public ArticleServiceImpl(ArticleRepository articleRepository, UtilisateurRepository utilisateurRepository, PrixArticleRepository prixArticleRepository) {
        this.articleRepository = articleRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.prixArticleRepository = prixArticleRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article getArticle(Long id) {
        return articleRepository.findById(id).get();
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> findByNomArticle(String nom) {
        return articleRepository.findByNomArticle(nom);
    }

    @Override
    public List<Article> findByNomArticleContains(String nom) {
        return articleRepository.findByNomArticleContains(nom);
    }

    @Override
    public List<Article> findByNomPrix(String nom, Double prix) {
        return articleRepository.findByNomPrix(nom, prix);
    }

    @Override
    public List<Article> findByCategorie(Categorie categorie) {
        return articleRepository.findByCategorie(categorie);
    }

    @Override
    public List<Article> findByCategorieIdCat(Long id) {
        return articleRepository.findByCategorieIdCat(id);
    }

    @Override
    public List<Article> findByOrderByNomArticleAsc() {
        return articleRepository.findByOrderByNomArticleAsc();
    }

    @Override
    public List<Article> trierArticlesNomsPrix() {
        return articleRepository.trierArticlesNomsPrix();
    }

    @Override
    public Article parametrerPrixArticle(Long articleId, Long idFancier, Float prix) {
        Article article = this.articleRepository.findById(articleId).orElseThrow();
        Utilisateur utilisateur = utilisateurRepository.findById(idFancier).orElseThrow();
        if (!article.isEstParametrer()){
            article.setPrix(prix);
            article.setEstParametrer(true);
            article.setEstParameterPar(utilisateur);
            this.articleRepository.save(article);
            return article;
        }
        return null;
    }

}
