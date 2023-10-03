package com.projet17backend.backend.repos;

import com.projet17backend.backend.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
