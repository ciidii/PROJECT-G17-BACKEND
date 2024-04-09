package com.projet17backend.backend.repos;

import com.projet17backend.backend.entities.EtatDeCaisse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatDeCaisseRepository extends CrudRepository<EtatDeCaisse, Long> {
}
