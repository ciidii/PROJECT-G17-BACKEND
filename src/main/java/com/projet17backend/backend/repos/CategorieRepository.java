package com.projet17backend.backend.repos;

import com.projet17backend.backend.entities.Categorie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
