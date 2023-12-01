package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.entities.Promo;
import com.projet17backend.backend.repos.ArticleRepository;
import com.projet17backend.backend.repos.PromoRepository;
import com.projet17backend.backend.services.PromoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoServiceImpl implements PromoService {
    private final ArticleRepository articleRepository;
    private final PromoRepository promoRepository;

    public PromoServiceImpl(ArticleRepository articleRepository, PromoRepository promoRepository) {
        this.articleRepository = articleRepository;
        this.promoRepository = promoRepository;
    }

    @Override
    public Promo parametre(Promo promo) {
        promo.getArticles().forEach(article -> {
            if (!articleRepository.existsById(article.getArticleId())) throw new RuntimeException("Attention cette article n'existe pas "+article.isEstParametrer());
        });
        if (promo.getDateDebut().isAfter(promo.getDateFin())) throw new RuntimeException("Attention la date de fin doit être après la date de début");
        if (promo.getTauxDeRemise()<0) throw new RuntimeException("Le aut de remise ne doit pas être négative");
        return this.promoRepository.save(promo);
    }

    @Override
    public List<Promo> lesPromos() {
        return this.promoRepository.findAll();
    }
}