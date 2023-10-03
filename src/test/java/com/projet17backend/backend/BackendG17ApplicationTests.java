package com.projet17backend.backend;

import com.projet17backend.backend.entities.Article;
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
		Article article = new Article(1L,"Article 1", "Article 1 Description", 10000L, true, 500.500f, new Date());
		articleRepository.save(article);
	}


}
