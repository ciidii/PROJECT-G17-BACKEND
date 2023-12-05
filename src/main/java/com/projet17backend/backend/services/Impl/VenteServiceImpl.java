package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.entities.DetailVente;
import com.projet17backend.backend.entities.Vente;
import com.projet17backend.backend.repos.ArticleRepository;
import com.projet17backend.backend.repos.UtilisateurRepository;
import com.projet17backend.backend.repos.VenteRepository;
import com.projet17backend.backend.services.VenteService;
import jakarta.xml.soap.Detail;
import org.springframework.stereotype.Service;

@Service
public class VenteServiceImpl implements VenteService {
    private final ArticleRepository articleRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final VenteRepository venteRepository;

    public VenteServiceImpl(ArticleRepository articleRepository, UtilisateurRepository utilisateurRepository, VenteRepository venteRepository) {
        this.articleRepository = articleRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.venteRepository = venteRepository;
    }

    @Override
    public Vente ajouter(Vente vente) {
        if (!utilisateurRepository.existsByIdUtilisateur(vente.getUtilisateur().getIdUtilisateur())) {
            throw new RuntimeException("Utilisateur n'existe pas");
        }


        vente.getDetailsVente().forEach(detailVente -> {
            if (this.articleRepository.existsById(detailVente.getArticle().getArticleId())) {
                if (detailVente.getArticle().getQttStock() >= detailVente.getQuantite()) {
                    Article article = detailVente.getArticle();
                    article.setQttStock(article.getQttStock() - detailVente.getQuantite());
                    this.articleRepository.save(article);
                } else {
                    throw new RuntimeException("La quantité restant est insuffisant");
                }
            } else {
                throw new RuntimeException("Article " + detailVente.getArticle().getNomArticle() + " n'existe pas");
            }
        });

        return venteRepository.save(vente);
    }
}
