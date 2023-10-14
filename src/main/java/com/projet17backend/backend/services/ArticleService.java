package com.projet17backend.backend.services;

import com.projet17backend.backend.entities.Article;

import java.util.List;

public interface ArticleService {

    Article saveArticle(Article article);
    Article getArticle(Long id);
    List<Article> getAllArticles();
    Article updateArticle(Article article);
    void deleteArticle(Article article);
    void deleteArticleById(Long id);

}
