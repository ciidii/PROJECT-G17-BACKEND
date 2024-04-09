package com.projet17backend.backend.mapper;

import com.projet17backend.backend.dto.DetailVenteDTO;
import com.projet17backend.backend.dto.InformationFinancierDTO;
import com.projet17backend.backend.dto.VenteDTO;
import com.projet17backend.backend.entities.DetailVente;
import com.projet17backend.backend.entities.Utilisateur;
import com.projet17backend.backend.entities.Vente;
import com.projet17backend.backend.repos.DetailVenteRepository;
import com.projet17backend.backend.repos.UtilisateurRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapVente {

    private final UtilisateurRepository utilisateurRepository;
    private final DetailVenteRepository detailVenteRepository;
    private final MapDetailVente mapDetailVente;

    public MapVente(UtilisateurRepository utilisateurRepository, DetailVenteRepository detailVenteRepository, MapDetailVente mapDetailVente) {
        this.utilisateurRepository = utilisateurRepository;
        this.detailVenteRepository = detailVenteRepository;
        this.mapDetailVente = mapDetailVente;
    }

    // Mapper un objet VenteDTO vers un objet Vente
    public Vente mapDtoToVente(VenteDTO venteDTO) {
        // Récupérer l'utilisateur depuis la base de données en utilisant l'ID de l'utilisateur
        Utilisateur utilisateur = utilisateurRepository.findById(venteDTO.getIdUtilisateur()).orElseThrow();

        // Créer un objet Vente en utilisant les données de VenteDTO
        Vente vente = new Vente();
        List<DetailVente> detailVenteList = new ArrayList<>();

        // Mapper chaque DetailVenteDTO de la liste DetailsVenteDTO à un DetailVente et l'ajouter à la liste
        venteDTO.getDetailsVente().forEach(detailVenteDTO -> {
            detailVenteList.add(mapDetailVente.mapDtoToDetailVente(detailVenteDTO));
        });

        vente.setIdVente(venteDTO.getIdVente());
        vente.setDetailsVente(detailVenteList);
        vente.setUtilisateur(utilisateur);
        vente.setDateDeVente(LocalDate.now());

        return vente;
    }

    // Mapper un objet Vente vers un objet VenteDTO
    public VenteDTO mapVenteToDto(Vente vente) {
        float totalVente = 0;
        // Créer un objet VenteDTO en utilisant les données de Vente
        VenteDTO venteDTO = new VenteDTO();
        venteDTO.setDateDeVente(vente.getDateDeVente());
        venteDTO.setIdUtilisateur(vente.getUtilisateur().getIdUtilisateur());
        venteDTO.setIdVente(vente.getIdVente());

        List<DetailVenteDTO> detailVenteDTOList = new ArrayList<>();

        // Mapper chaque DetailVente de la liste DetailsVente à un DetailVenteDTO et l'ajouter à la liste
        vente.getDetailsVente().forEach(detailVente -> {
            float totalVente1 = detailVente.getPrixUnitaire() * detailVente.getQuantite() + venteDTO.getTotalVente();
            venteDTO.setTotalVente(totalVente1);
            detailVenteDTOList.add(mapDetailVente.mapDetailVenteToDto(detailVente));
        });

        venteDTO.setDetailsVente(detailVenteDTOList);

        return venteDTO;
    }
    public InformationFinancierDTO mapVentesToInformationFinancierDto(Vente vente){
        InformationFinancierDTO informationFinancierDTO = new InformationFinancierDTO();
        return null;
    }
}
