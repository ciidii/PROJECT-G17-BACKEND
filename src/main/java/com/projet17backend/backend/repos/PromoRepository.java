package com.projet17backend.backend.repos;

import com.projet17backend.backend.entities.Promo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromoRepository extends CrudRepository<Promo,Long> {
    List<Promo> findAll();
}