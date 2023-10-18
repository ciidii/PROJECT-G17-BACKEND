package com.projet17backend.backend.dto;

import com.projet17backend.backend.entities.ROLE;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UtilisateurDTO(
        Long idUtilisateur,
        @NotNull(message = "Nom est Obligatoire")
        String nom,
        @NotNull(message = "Prenom est Obligatoire")
        String prenom,
        @NotNull
        String numeroTel,
        @Email(message = "Email invalide")
        @NotNull(message = "Email est Obligatoire")
        String email,
        String identifiant,
        String motDePasse,
        @NotNull(message = "l'adresse est Obligatoire")
        String adresse,
        @NotNull(message = "Preciser le rôle")
        @Enumerated(EnumType.STRING)
        ROLE role,
        boolean activated,
        boolean premierConnexion
) {
}
