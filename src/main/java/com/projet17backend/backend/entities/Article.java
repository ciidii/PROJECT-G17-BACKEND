package com.projet17backend.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    private String nomArticle;
    private String descriptionArticle;
    private Long qttStock;
    private boolean estVendable;
    private float prix;
    private Date dateCreation;

    @ManyToOne
    private Categorie categorie;
}
