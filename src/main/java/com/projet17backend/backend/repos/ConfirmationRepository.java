package com.projet17backend.backend.repos;

import com.projet17backend.backend.entities.Confirmation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends CrudRepository<Confirmation,Long> {
    public boolean existsByToken(String token);
}
