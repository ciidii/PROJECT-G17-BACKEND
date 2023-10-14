package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.repos.ArticleRepository;
import com.projet17backend.backend.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
