package com.projet17backend.backend.repos;

import com.projet17backend.backend.entities.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur,Long> {
    public Optional<Utilisateur> findByIdentifiant(String identifiant);


}
