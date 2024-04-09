package com.projet17backend.backend.repos;

import com.projet17backend.backend.entities.Alert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlertRepository extends CrudRepository<Alert,Long> {
    public List<Alert> findAll();
}
