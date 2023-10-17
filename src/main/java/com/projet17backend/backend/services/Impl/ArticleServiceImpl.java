package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.entities.Categorie;
import com.projet17backend.backend.repos.ArticleRepository;
import com.projet17backend.backend.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;
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
}
