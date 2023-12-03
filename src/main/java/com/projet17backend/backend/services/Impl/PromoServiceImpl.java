package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.dto.UtilisateurDTO;
import com.projet17backend.backend.entities.Promo;
import com.projet17backend.backend.mapper.MapUtilisateur;
import com.projet17backend.backend.repos.ArticleRepository;
import com.projet17backend.backend.repos.PromoRepository;
import com.projet17backend.backend.services.PromoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoServiceImpl implements PromoService {
    private final ArticleRepository articleRepository;
    private final PromoRepository promoRepository;
    private final MapUtilisateur mapUtilisateur;
    public PromoServiceImpl(ArticleRepository articleRepository, PromoRepository promoRepository, MapUtilisateur mapUtilisateur) {
        this.articleRepository = articleRepository;
        this.promoRepository = promoRepository;
        this.mapUtilisateur = mapUtilisateur;
    }

    @Override
    public Promo parametre(Promo promo) {
        promo.getArticles().forEach(article -> {
            if (!articleRepository.existsById(article.getArticleId())) throw new RuntimeException("Attention cette article n'existe pas "+article.getNomArticle());
            if (!promoRepository.findActivePromosForArticle(article).contains(article.getArticleId())) throw new RuntimeException("Attention ! une article ne peut faire de plusieurs fois en même temps.");
        });
        if (promo.getDateDebut().isAfter(promo.getDateFin())) throw new RuntimeException("Attention la date de fin doit être après la date de début");
        if (promo.getTauxDeRemise()<0) throw new RuntimeException("Le aut de remise ne doit pas être négative");
        return this.promoRepository.save(promo);
    }

    @Override
    public List<Promo> lesPromos() {
        return this.promoRepository.findAll();
    }

    @Override
    public Promo modifier(Promo promo) {
        promo.getArticles().forEach(article -> {
            if (!articleRepository.existsById(article.getArticleId())) throw new RuntimeException("Attention cette article n'existe pas "+article.getNomArticle());
            if (!promoRepository.findActivePromosForArticle(article).contains(article.getArticleId())) throw new RuntimeException("Attention ! une article ne peut faire de plusieurs fois en même temps.");
        });
        if (promo.getDateDebut().isAfter(promo.getDateFin())) throw new RuntimeException("Attention la date de fin doit être après la date de début");
        if (promo.getTauxDeRemise()<0) throw new RuntimeException("Le aut de remise ne doit pas être négative");
        return this.promoRepository.save(promo);
    }
}