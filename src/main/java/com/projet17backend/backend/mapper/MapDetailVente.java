package com.projet17backend.backend.mapper;

import com.projet17backend.backend.dto.DetailVenteDTO;
import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.entities.DetailVente;
import com.projet17backend.backend.repos.ArticleRepository;
import org.springframework.stereotype.Component;

@Component
public class MapDetailVente {

    private final ArticleRepository articleRepository;

    public MapDetailVente(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // Mapper un objet DetailVenteDTO vers un objet DetailVente
    public DetailVente mapDtoToDetailVente(DetailVenteDTO detailVenteDTO) {
        // Récupérer l'article depuis la base de données en utilisant l'ID
        Article article = articleRepository.findById(detailVenteDTO.getArticleId()).orElseThrow();

        // Créer un objet DetailVente en utilisant les données de DetailVenteDTO
        DetailVente detailVente = new DetailVente();
        detailVente.setIdDetailVente(detailVenteDTO.getIdDetailVente());
        detailVente.setQuantite(detailVenteDTO.getQuantite());
        detailVente.setArticle(article);

        return detailVente;
    }

    // Mapper un objet DetailVente vers un objet DetailVenteDTO
    public DetailVenteDTO mapDetailVenteToDto(DetailVente detailVente) {
        // Créer un objet DetailVenteDTO en utilisant les données de DetailVente
        DetailVenteDTO detailVenteDTO = new DetailVenteDTO();
        detailVenteDTO.setIdDetailVente(detailVente.getIdDetailVente());
        detailVenteDTO.setQuantite(detailVente.getQuantite());

        // Remarque : si tu ne veux pas inclure l'ID de l'article dans le DTO, tu peux l'omettre
        detailVenteDTO.setArticleId(detailVente.getArticle().getArticleId());

        // Remarque : si tu ne veux pas inclure l'ID de la vente dans le DTO, tu peux l'omettre

        detailVenteDTO.setVenteId(detailVente.getVente().getIdVente());

        return detailVenteDTO;
    }
}
