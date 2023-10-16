package com.projet17backend.backend.restControllers;

import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ArticleRESTController {

    @Autowired
    ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Article> getAllArticles(){
        return articleService.getAllArticles();
    }

    @RequestMapping(value ="/{id}",method = RequestMethod.GET)
    public Article getArticleById(@PathVariable("id") Long id){
        return articleService.getArticle(id);
    }

    @RequestMapping(value ="/create",method = RequestMethod.POST)
    public Article createArticle(@RequestBody Article article){
        return articleService.saveArticle(article);
    }

    @RequestMapping(value ="/update", method = RequestMethod.PUT)
    public Article updateArticle(@RequestBody Article article){
        return articleService.updateArticle(article);
    }

    @RequestMapping(value ="/delete/{id}", method = RequestMethod.DELETE)
    public void deleteArticle(@PathVariable("id") Long id){
        articleService.deleteArticleById(id);
    }

    @RequestMapping(value ="/articles/{idCat}", method = RequestMethod.GET)
    public List<Article> getArticleList(@PathVariable("idCat") Long id){
        return articleService.findByCategorieIdCat(id);
    }
}
