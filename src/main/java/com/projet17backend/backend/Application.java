package com.projet17backend.backend;

import com.projet17backend.backend.entities.Article;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final RepositoryRestConfiguration repositoryRestConfiguration;

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public Application(RepositoryRestConfiguration repositoryRestConfiguration) {
        this.repositoryRestConfiguration = repositoryRestConfiguration;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //Par défaut Spring Data REST ne retourne pas la propriéte ID. cette methode permet de retourner l'ID
    @Override
    public void run(String[] args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Article.class);
    }
}
