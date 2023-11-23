package com.projet17backend.backend.repos;

import com.projet17backend.backend.entities.LogArticles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogArticleRepository extends CrudRepository<LogArticles, Long> {
}
