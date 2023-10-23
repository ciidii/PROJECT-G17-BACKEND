package com.projet17backend.backend;

import com.projet17backend.backend.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class BackendG17Application implements CommandLineRunner {

	private final RepositoryRestConfiguration repositoryRestConfiguration;

	public BackendG17Application(RepositoryRestConfiguration repositoryRestConfiguration) {
		this.repositoryRestConfiguration = repositoryRestConfiguration;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendG17Application.class, args);
	}

	//Par défaut Spring Data REST ne retourne pas la propriéte ID. cette methode permet de retourner l'ID
	@Override
	public void run(String[] args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Article.class);
	}

}
