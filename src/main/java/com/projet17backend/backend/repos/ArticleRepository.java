package com.projet17backend.backend.repos;

import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

// @RepositoryRestResource(path = "rest")
//Quelle est la difference entre repository et RepositoryRestResource
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByNomArticle(String nom);

    List<Article> findByNomArticleContains(String nom);

    @Query("select a from Article a where a.nomArticle like %?1 and a.prix > ?2")
    List<Article> findByNomPrix(String nom, Double prix);

    @Query("select a from Article a where a.categorie = ?1")
    List<Article> findByCategorie(Categorie categorie);

    List<Article> findByCategorieIdCat(Long id);

    List<Article> findByOrderByNomArticleAsc();

    @Query("select a from Article a order by a.nomArticle ASC, a.prix DESC")
    List<Article> trierArticlesNomsPrix();
}
