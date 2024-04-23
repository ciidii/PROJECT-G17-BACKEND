
package com.projet17backend.backend.controllers;

import com.projet17backend.backend.dto.AjoutArticlesDTO;
import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.services.ArticleService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleRESTController {

    private final ArticleService articleService;

    public ArticleRESTController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Article getArticleById(@PathVariable("id") Long id) {
        return articleService.getArticle(id);
    }


    @PostMapping(value = "/create/{idUtilisateur}")
    public Article createArticle(
            @PathVariable("idUtilisateur") Long idUtilisateur,
            @RequestParam("nomArticle") String nomArticle,
            @RequestParam("descriptionArticle") String descriptionArticle,
            @RequestParam("qttStock") Long qttStock,
            @RequestParam("idCategorie") Long idCategorie,
            @RequestPart("images") List<MultipartFile> images
    ) {
        AjoutArticlesDTO article = new AjoutArticlesDTO(nomArticle, descriptionArticle, qttStock, idCategorie);
        return articleService.saveArticle(article, idUtilisateur, images);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Article updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteArticleById(id);
    }

    @RequestMapping(value = "/articles/{idCat}", method = RequestMethod.GET)
    public List<Article> getArticleList(@PathVariable("idCat") Long id) {
        return articleService.findByCategorieIdCat(id);
    }

    @RequestMapping(value = "/financier/parmetrerPrix", method = RequestMethod.PUT)
    public Article parmetrerPrixArticle(@Valid @RequestParam("articleID") Long articleID, @RequestParam("idFinancier") Long idFinancier, @RequestParam("prix") Float prix) {
        return this.articleService.parametrerPrixArticle(articleID, idFinancier, prix);
    }

    @PostMapping(value = "/add-image")
    public ResponseEntity<?> uploadImage(

            @RequestParam("article-id") Long articleId,
            @RequestParam("user-id") Long userID,
            @RequestPart("files") List<MultipartFile> files

    ) {
        this.articleService.uploadImage(articleId, userID, files);
        return ResponseEntity.accepted().build();
    }
}