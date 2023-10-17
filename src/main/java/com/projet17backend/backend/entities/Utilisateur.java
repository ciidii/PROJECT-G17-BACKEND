package com.projet17backend.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    @Column(name = "numero_tel")
    private String numeroTel;
    private String email;
    private String identifiant;
    @Column(name = "not_de_passe")
    private String motDePasse;
    private String adresse;
    @Enumerated(EnumType.STRING)
    private ROLE  role;
    private Boolean activated=false;
    private boolean premierConnexion=true;

}
