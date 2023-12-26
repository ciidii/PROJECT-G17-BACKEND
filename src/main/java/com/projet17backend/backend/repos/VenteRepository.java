package com.projet17backend.backend.repos;

import com.projet17backend.backend.entities.Utilisateur;
import com.projet17backend.backend.entities.Vente;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VenteRepository extends CrudRepository<Vente,Long> {
    public Optional<List<Vente>> findAllByUtilisateurAndDateDeVente(Utilisateur utilisateur, LocalDate date);
}
