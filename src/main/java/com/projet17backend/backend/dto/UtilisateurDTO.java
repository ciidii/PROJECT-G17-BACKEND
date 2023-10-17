package com.projet17backend.backend.dto;

import com.projet17backend.backend.entities.ROLE;

public record UtilisateurDTO(
        Long idUtilisateur,
        String nom,
        String prenom,
        String numeroTel,
        String email,
        String identifiant,
        String motDePasse,
        String adresse,
        ROLE role,
        boolean activated,
        boolean premierConnexion
) {
}
