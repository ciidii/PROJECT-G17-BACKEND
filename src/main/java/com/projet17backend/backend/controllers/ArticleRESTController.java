
package com.projet17backend.backend.controllers;

import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.services.ArticleService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
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

    @RequestMapping(value = "/create/{idUtilisateur}", method = RequestMethod.POST)
    public Article createArticle(@Valid @RequestBody Article article, @PathVariable("idUtilisateur") Long idUtilisateur) {
        return articleService.saveArticle(article,idUtilisateur);
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

}