package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.dto.*;
import com.projet17backend.backend.entities.Article;
import com.projet17backend.backend.entities.Utilisateur;
import com.projet17backend.backend.entities.Vente;
import com.projet17backend.backend.mapper.MapEtatDeCaisse;
import com.projet17backend.backend.mapper.MapVente;
import com.projet17backend.backend.repos.*;
import com.projet17backend.backend.services.EtatDeCaisseService;
import com.projet17backend.backend.services.VenteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VenteServiceImpl implements VenteService {

    private final ArticleRepository articleRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final VenteRepository venteRepository;
    private final DetailVenteRepository detailVenteRepository;
    private final MapVente mapVente;
    private final EtatDeCaisseService etatDeCaisseService;
    private final EtatDeCaisseRepository etatDeCaisseRepository;
    private final MapEtatDeCaisse mapEtatDeCaisse;

    public VenteServiceImpl(ArticleRepository articleRepository, UtilisateurRepository utilisateurRepository,
                            VenteRepository venteRepository, DetailVenteRepository detailVenteRepository,
                            MapVente mapVente, EtatDeCaisseService etatDeCaisseService, EtatDeCaisseRepository etatDeCaisseRepository, MapEtatDeCaisse mapEtatDeCaisse) {
        this.articleRepository = articleRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.venteRepository = venteRepository;
        this.detailVenteRepository = detailVenteRepository;
        this.mapVente = mapVente;
        this.etatDeCaisseService = etatDeCaisseService;
        this.etatDeCaisseRepository = etatDeCaisseRepository;
        this.mapEtatDeCaisse = mapEtatDeCaisse;
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

    @Override
    public MesVentesDto trouveVenteUtilisateur(Long utilisateurId, LocalDate date) {
        valideUtilisateur(utilisateurId);
        Utilisateur utilisateur = this.utilisateurRepository.findByIdUtilisateur(utilisateurId);
        List<Vente> ventes = getVentes(date, utilisateur);
        MesVentesDto mesVentesDto = new MesVentesDto();
        mesVentesDto.setNbVente((long) ventes.size());
        List<VenteDTO> venteDTOS = new ArrayList<>();
        ventes.forEach(vente -> {
            venteDTOS.add(this.mapVente.mapVenteToDto(vente));
        });
        mesVentesDto.setVenteDTOS(venteDTOS);
        return mesVentesDto;
    }

    @Override
    public EtatDeCaisseDTO sortirEcartDeCaisse(Long utilisateur, float sommeEnCaisse, Boolean envoyer) {
        final float[] somme = {0};
        valideUtilisateur(utilisateur);
        List<Vente> ventes = this.getVentes(LocalDate.now(), this.utilisateurRepository.findByIdUtilisateur(utilisateur));
        List<VenteDTO> venteDTOS = new ArrayList<>();
        ventes.forEach(vente -> {
            venteDTOS.add(this.mapVente.mapVenteToDto(vente));
        });
        venteDTOS.forEach(venteDTO -> {
            somme[0] = somme[0] + venteDTO.getTotalVente();
        });
        EtatDeCaisseDTO etatDeCaisseDTO = new EtatDeCaisseDTO();
        etatDeCaisseDTO.setEcartDeCaisse(somme[0] - sommeEnCaisse);
        etatDeCaisseDTO.setSommeAttendus(somme[0]);
        etatDeCaisseDTO.setSommeDansLaCaisse(sommeEnCaisse);
        if (envoyer) {
            return this.etatDeCaisseService.ajouter(etatDeCaisseDTO);
        }
        return etatDeCaisseDTO;
    }

    @Override
    public EtatDeCaisseDTO validateEtatDeCaisse(Long adminOrFinancier, Long etatDeCaisse, Boolean validate) {
        valideUtilisateur(adminOrFinancier);
        return this.etatDeCaisseService.validateEtatDeCaisse(adminOrFinancier, etatDeCaisse, validate);
    }

    @Override
    public List<EtatDeCaisseDTO> afficherTous() {
        List<EtatDeCaisseDTO> etatDeCaisseDTOS = new ArrayList<>();
        this.etatDeCaisseRepository.findAll().forEach(etatDeCaisse -> {
            etatDeCaisseDTOS.add(this.mapEtatDeCaisse.mapEtatDeCaisseToDTO(etatDeCaisse));
        });
        return etatDeCaisseDTOS;
    }

    @Override
    public InformationFinancierDTO suivreInformationFinancier(Long idFinancier, LocalDate dateUtilisateur) {
        valideUtilisateur(idFinancier);
        List<Vente> ventes = this.venteRepository.findAllByDateDeVente(dateUtilisateur).orElseThrow();
        InformationFinancierDTO informationFinancierDTO = new InformationFinancierDTO();
        informationFinancierDTO.setDate(dateUtilisateur);
        informationFinancierDTO.setNombreVentes(ventes.size());
        ventes.forEach(vente -> {
            vente.getDetailsVente().forEach(detailVente -> {
                int nombreDArticle = informationFinancierDTO.getNombreDArticle() + detailVente.getQuantite();
                informationFinancierDTO.setNombreDArticle(nombreDArticle);
                float totalVente = informationFinancierDTO.getTotalVente() + detailVente.getPrixUnitaire() * detailVente.getQuantite();
                informationFinancierDTO.setTotalVente(totalVente);
            });
        });
        return informationFinancierDTO;
    }

    // Méthode pour valider si un utilisateur existe
    private void valideUtilisateur(Long idUtilisateur) {
        if (!utilisateurRepository.existsByIdUtilisateur(idUtilisateur)) {
            throw new RuntimeException("Utilisateur n'existe pas");
        }
    }

    private List<Vente> getVentes(LocalDate date, Utilisateur utilisateur) {
        List<Vente> ventes = this.venteRepository.findAllByUtilisateurAndDateDeVente(utilisateur, date).orElseThrow();
        this.venteRepository.findAllByUtilisateurAndDateDeVente(utilisateur, date).isEmpty();
        if (ventes.isEmpty()) {
            throw new RuntimeException("Vous avez effectué aucun vente le: " + date);
        }
        return ventes;
    }

    // Méthode pour traiter chaque détail de vente
    private void processArticleDetail(DetailVenteDTO detailVenteDTO) {
        // Récupérer l'article depuis la base de données en utilisant l'ID de l'article
        Article article = articleRepository.findById(detailVenteDTO.getArticleId())
                .orElseThrow(() -> new RuntimeException("Article " + detailVenteDTO.getArticleId() + " n'existe pas"));
        if (!article.isEstParametrer()) {
            throw new RuntimeException("Les prix de " + article.getNomArticle() + " n'est pas paramétrer");
        }

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