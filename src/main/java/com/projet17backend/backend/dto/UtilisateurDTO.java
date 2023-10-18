package com.projet17backend.backend.dto;

import com.projet17backend.backend.entities.ROLE;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDTO {
        private Long idUtilisateur;
        @NotNull(message = "Nom est Obligatoire")
        private String nom;
        @NotNull(message = "Prenom est Obligatoire")
        private String prenom;
        @NotNull
        private String numeroTel;
        @Email(message = "Email invalide")
        @NotNull(message = "Email est Obligatoire")
        private String email;
        private String identifiant;
        private String motDePasse;
        @NotNull(message = "l'adresse est Obligatoire")
        private String adresse;
        @NotNull(message = "Preciser le rôle")
        @Enumerated(EnumType.STRING)
        private  ROLE role;
        private boolean activated;
        private  boolean premierConnexion;
}
