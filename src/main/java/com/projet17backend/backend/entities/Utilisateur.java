package com.projet17backend.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateure {
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    private String numeroTel;
    private String email;
    private String identifiant;
    private String motDePasse;

}
