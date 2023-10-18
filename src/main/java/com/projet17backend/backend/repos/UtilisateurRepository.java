package com.projet17backend.backend.repos;

import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.entities.Utilisateur;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur,Long> {
    public Optional<Utilisateur> findByIdentifiant(String identifiant);
    public Optional<Utilisateur> findByEmail(String email);
    public List<Utilisateur> findAll(Sort sort);
}
