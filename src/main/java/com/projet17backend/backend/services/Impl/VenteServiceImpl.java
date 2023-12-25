package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.dto.DetailVenteDTO;
import com.projet17backend.backend.dto.VenteDTO;
import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.entities.Vente;
import com.projet17backend.backend.mapper.MapVente;
import com.projet17backend.backend.repos.ArticleRepository;
import com.projet17backend.backend.repos.DetailVenteRepository;
import com.projet17backend.backend.repos.UtilisateurRepository;
import com.projet17backend.backend.repos.VenteRepository;
import com.projet17backend.backend.services.VenteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VenteServiceImpl implements VenteService {

    private final ArticleRepository articleRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final VenteRepository venteRepository;
    private final DetailVenteRepository detailVenteRepository;
    private final MapVente mapVente;

    public VenteServiceImpl(ArticleRepository articleRepository, UtilisateurRepository utilisateurRepository,
                            VenteRepository venteRepository, DetailVenteRepository detailVenteRepository,
                            MapVente mapVente) {
        this.articleRepository = articleRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.venteRepository = venteRepository;
        this.detailVenteRepository = detailVenteRepository;
        this.mapVente = mapVente;
    }

    // Méthode pour ajouter une nouvelle vente
    @Override
    @Transactional  // Annotation pour gérer automatiquement les transactions
    public VenteDTO ajouter(VenteDTO venteDTO) {
        // Vérifier si l'utilisateur existe
        valideUtilisateur(venteDTO.getIdUtilisateur());

        // Traiter chaque détail de vente
        venteDTO.getDetailsVente().forEach(this::processArticleDetail);

        // Mapper le DTO de vente vers l'entité de vente
        Vente vente = this.mapVente.mapDtoToVente(venteDTO);

        // Sauvegarder la vente et ses détails
        saveSaleAndDetails(vente);

        // Mapper l'entité de vente vers le DTO de vente et le retourner
        return this.mapVente.mapVenteToDto(vente);
    }

    // Méthode pour valider si un utilisateur existe
    private void valideUtilisateur(Long idUtilisateur) {
        if (!utilisateurRepository.existsByIdUtilisateur(idUtilisateur)) {
            throw new RuntimeException("Utilisateur n'existe pas");
        }
    }

    // Méthode pour traiter chaque détail de vente
    private void processArticleDetail(DetailVenteDTO detailVenteDTO) {
        // Récupérer l'article depuis la base de données en utilisant l'ID de l'article
        Article article = articleRepository.findById(detailVenteDTO.getArticleId())
                .orElseThrow(() -> new RuntimeException("Article " + detailVenteDTO.getArticleId() + " n'existe pas"));

        // Vérifier si la quantité en stock est suffisante
        if (article.getQttStock() >= detailVenteDTO.getQuantite()) {
            // Décrémenter la quantité en stock de l'article
            article.setQttStock(article.getQttStock() - detailVenteDTO.getQuantite());
            // Sauvegarder l'article
            articleRepository.save(article);
        } else {
            throw new RuntimeException("La quantité restante est insuffisante");
        }
    }

    // Méthode pour sauvegarder une vente et ses détails
    private void saveSaleAndDetails(Vente vente) {
        // Sauvegarder la vente
        venteRepository.save(vente);

        // Pour chaque détail de vente, définir la vente associée et sauvegarder
        vente.getDetailsVente().forEach(detailVente -> {
            detailVente.setVente(vente);
            detailVenteRepository.save(detailVente);
        });
    }
}