package com.projet17backend.backend;

import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.entities.Categorie;
import com.projet17backend.backend.repos.ArticleRepository;
import com.projet17backend.backend.repos.CategorieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class BackendG17ApplicationTests {
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CategorieRepository categorieRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateArticle() {
		Article article = new Article("Article 4", "Article 4 Description", 10000L, true, 500.500f, new Date());
		articleRepository.save(article);
	}

	@Test
	public void findArticle() {
		Article article = articleRepository.findById(1L).get();
		System.out.println(article);
	}

	/*
	@Test
	public void testUpdateArticle()
	{
		Categorie cat = categorieRepository.findById(1L).orElseThrow();
		List<Article> articles =null;
		if (cat != null)
		{
			 articles = articleRepository.findByCategorieIdCat(cat.getIdCat());
			 cat.setArticles(articles);
		}
		Article article = articleRepository.findById(6L).get();
		article.setCategorie(cat);
		articleRepository.save(article);
		articleRepository.flush();
		System.out.println(article);
	}


	 */

	@Test
	public void testGetAllArticles(){
		List<Article> articles = articleRepository.findAll();
		for (Article article : articles){
			System.out.println(articles);
		}
	}

	@Test
	public void testDeleteArticle(){
		articleRepository.deleteById(3L);
	}

	@Test public void testfindByNomPrix()
	{
		List<Article> arts = articleRepository.findByNomPrix("Article 1", 500.5);
		for (Article a : arts)
		{
			System.out.println(a);
		}
	}

	@Test
	public void testfindByCategorie()
	{
		Categorie cat = new Categorie();
		cat.setIdCat(2L);
		List<Article> arts = articleRepository.findByCategorie(cat);
		for (Article a : arts)
		{
			System.out.println(a.getNomArticle());
		}
	}

	@Test
	public void findByCategorieIdCat()
	{
		List<Article> arts = articleRepository.findByCategorieIdCat(1L);
		for (Article a : arts)
		{
			System.out.println(a.getNomArticle());
		}
	}

	@Test
	public void testTrierProduitsNomsPrix()
	{
		List<Article> arts = articleRepository.trierArticlesNomsPrix();
		for (Article a : arts)
		{
			System.out.println(a.getNomArticle());
		}
	}

//	@Test
//	public void testCreateCategorie() {
//		Categorie category = new Categorie(1L,"Category 1", "Category 1 Description", null);
//	}

}
