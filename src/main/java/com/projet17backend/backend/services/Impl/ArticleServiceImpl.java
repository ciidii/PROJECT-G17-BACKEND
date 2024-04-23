package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.dto.AjoutArticlesDTO;
import com.projet17backend.backend.entities.*;
import com.projet17backend.backend.repos.ArticleRepository;
import com.projet17backend.backend.repos.CategorieRepository;
import com.projet17backend.backend.repos.LogArticleRepository;
import com.projet17backend.backend.repos.UtilisateurRepository;
import com.projet17backend.backend.services.ArticleService;
import com.projet17backend.backend.services.FileStorageService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final LogArticleRepository logArticleRepository;
    private final CategorieRepository categorieRepository;
    private final FileStorageService fileStorageService;


    public ArticleServiceImpl(
            ArticleRepository articleRepository,
            UtilisateurRepository utilisateurRepository,
            LogArticleRepository logArticleRepository,
            CategorieRepository categorieRepository,
            FileStorageService fileStorageService) {
        this.articleRepository = articleRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.logArticleRepository = logArticleRepository;
        this.categorieRepository = categorieRepository;
        this.fileStorageService = fileStorageService;
        ModelMapper modelMapper = new ModelMapper();
    }

    @Override
    public Article saveArticle(AjoutArticlesDTO ajoutArticlesDTO, Long utilisateurId,List<MultipartFile> images) {
        Article article1 = new Article();
        Categorie categorie = this.categorieRepository.findById(ajoutArticlesDTO.getIdCategorie()).orElseThrow();
        article1.setNomArticle(ajoutArticlesDTO.getNomArticle());
        article1.setDescriptionArticle(ajoutArticlesDTO.getDescriptionArticle());
        article1.setQttStock(ajoutArticlesDTO.getQttStock());
        article1.setCategorie(categorie);
        Utilisateur utilisateur = this.utilisateurRepository.findById(utilisateurId).orElseThrow();
        List<String> imagesPaths = this.fileStorageService.saveFile(images,utilisateur.getIdUtilisateur());
        article1.setImages(imagesPaths);
        Article articleSaved = articleRepository.save(article1);

        this.logArticleActions(utilisateur, articleSaved, OPERATION_ARTICLES.AJOUT);
        return articleSaved;
    }

    @Override
    public Article getArticle(Long id) {
        return articleRepository.findById(id).get();
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> findByNomArticle(String nom) {
        return articleRepository.findByNomArticle(nom);
    }

    @Override
    public List<Article> findByNomArticleContains(String nom) {
        return articleRepository.findByNomArticleContains(nom);
    }

    @Override
    public List<Article> findByNomPrix(String nom, Double prix) {
        return articleRepository.findByNomPrix(nom, prix);
    }

    @Override
    public List<Article> findByCategorie(Categorie categorie) {
        return articleRepository.findByCategorie(categorie);
    }

    @Override
    public List<Article> findByCategorieIdCat(Long id) {
        return articleRepository.findByCategorieIdCat(id);
    }

    @Override
    public List<Article> findByOrderByNomArticleAsc() {
        return articleRepository.findByOrderByNomArticleAsc();
    }

    @Override
    public List<Article> trierArticlesNomsPrix() {
        return articleRepository.trierArticlesNomsPrix();
    }

    @Override
    public Article parametrerPrixArticle(Long articleId, Long idFancier, Float prix) {
        Article article = this.articleRepository.findById(articleId).orElseThrow();
        Utilisateur utilisateur = this.utilisateurRepository.findById(idFancier).orElseThrow();
        if (!article.isEstParametrer()) {
            article.setPrix(prix);
            article.setEstParametrer(true);
            this.articleRepository.save(article);
            logArticleActions(utilisateur, article, OPERATION_ARTICLES.PARAMETRER);
            return article;
        } else {
            //Si l'article est déja paramétrer on modifier le prix
            article.setPrix(prix);
            this.articleRepository.save(article);
            logArticleActions(utilisateur, article, OPERATION_ARTICLES.MODIER);
            return article;
            // TODO: 23/11/2023 je doit ajouter les modifications dans la table des logs de prix
        }
    }

    @Override
    public Article rendreVendable(Article article, Long idFinancier) {
        if (this.utilisateurRepository.existsById(idFinancier) && this.articleRepository.existsById(article.getArticleId())) {
            article.setEstVendable(!article.isEstVendable());
            this.articleRepository.save(article);
            logArticleActions(this.utilisateurRepository.findByIdUtilisateur(idFinancier), article, OPERATION_ARTICLES.RENDRE_VENDABLE);
            return article;
        } else throw new RuntimeException("Données incorrectent");
    }

    @Override
    public void uploadImage(Long articleId, Long userID, List<MultipartFile> files) {
        Article article = this.articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("Article introuvable"));
        Utilisateur utilisateur = this.utilisateurRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException("L'utilisateur n'existe pas ou n'a pas le droit d'effectuer cet action"));
        List<String> articleImages = fileStorageService.saveFile(files, utilisateur.getIdUtilisateur());
        article.setImages(articleImages);
        this.articleRepository.save(article);
    }

    private void logArticleActions(Utilisateur utilisateur, Article article, OPERATION_ARTICLES actions) {
        LogArticles logArticles = new LogArticles();
        logArticles.setUtilisateur(utilisateur);
        logArticles.setTypeOperation(actions);
        logArticles.setArticle(article);
        this.logArticleRepository.save(logArticles);
    }

}
