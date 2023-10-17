package com.projet17backend.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    private String numeroTel;
    private String email;
    private String identifiant;
    private String motDePasse;
    private String adresse;
    @Enumerated(EnumType.STRING)
    private ROLE  role;
    private Boolean activated;
    private boolean premierConnexion;

}
