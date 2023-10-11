package com.projet17backend.backend;

import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.entities.Categorie;
import com.projet17backend.backend.repos.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class BackendG17ApplicationTests {
	@Autowired
	private ArticleRepository articleRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateArticle() {
		Article article = new Article(2L,"Article 3", "Article 3 Description", 10000L, true, 500.500f, new Date(), null);
		articleRepository.save(article);
	}

	@Test
	public void testCreateCategorie() {
		Categorie category = new Categorie(1L,"Category 1", "Category 1 Description", null);
	}

}
