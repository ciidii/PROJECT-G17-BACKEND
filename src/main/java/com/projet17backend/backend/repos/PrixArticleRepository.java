package com.projet17backend.backend.repos;

import com.projet17backend.backend.entities.AgentPrix;
import org.springframework.data.repository.CrudRepository;

public interface PrixArticleRepository extends CrudRepository<AgentPrix,Long> {
}
