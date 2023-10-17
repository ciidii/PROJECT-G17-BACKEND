package com.projet17backend.backend.dto;

import com.projet17backend.backend.entities.ROLE;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record UtilisateurDTO(
        Long idUtilisateur,
        String nom,
        String prenom,
        String numeroTel,
        String email,
        String identifiant,
        String motDePasse,
        String adresse,
        @Enumerated(EnumType.STRING)
        ROLE role,
        boolean activated,
        boolean premierConnexion
) {
}
