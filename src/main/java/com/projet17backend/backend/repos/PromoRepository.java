package com.projet17backend.backend.repos;

import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.entities.Promo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromoRepository extends CrudRepository<Promo,Long> {
    List<Promo> findAll();
    @Query("SELECT p FROM Promo p JOIN p.articles a WHERE a = :article AND p.dateDebut <= CURRENT_DATE AND p.dateFin >= CURRENT_DATE")
    List<Promo> findActivePromosForArticle(@Param("article") Article article);
}