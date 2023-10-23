    package com.projet17backend.backend.services.Impl;

    import com.projet17backend.backend.dto.UtilisateurDTO;
    import com.projet17backend.backend.entities.ROLE;
    import com.projet17backend.backend.entities.Utilisateur;
    import com.projet17backend.backend.mapper.MapUtilisateur;
    import com.projet17backend.backend.repos.UtilisateurRepository;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.Mock;
    import org.mockito.Mockito;
    import org.mockito.junit.jupiter.MockitoExtension;
    import org.springframework.data.domain.Sort;

    @ExtendWith(MockitoExtension.class)
    class UtilisateurServiceTest {
        @Mock
        private UtilisateurRepository utilisateurRepository;
        @Mock
        private MapUtilisateur mapUtilisateur;
        private UtilisateurService underTest;
        private Utilisateur utilisateur;
        private UtilisateurDTO utilisateurDTO;
        @BeforeEach
        void setUp(){
            underTest = new UtilisateurService(utilisateurRepository,mapUtilisateur);
             utilisateur =   new Utilisateur(
                    null,
                    "Diallo",
                    "Boubacar",
                    "782156437",
                    "boubacarcidi77@gmail.com",
                    "G17GB001",
                    "passer",
                    "Malika",
                    ROLE.ROLE_ADMIN,
                    false,
                    true
            );
             utilisateurDTO = new UtilisateurDTO(
                    null,
                    "Diallo",
                    "Boubacar",
                    "782156437",
                    "boubacar2cidi77@gmail.com",
                    "G17GB002",
                    "passer",
                    "Malika",
                    ROLE.ROLE_ADMIN,
                    false,
                    true
            );
        }

        @Test
        void peutRetounerTousLesUtilisateur(){
            //WHEN
            underTest.utilisateurs();
            //THEN
            Mockito.verify(utilisateurRepository).findAll(Sort.by("idUtilisateur"));
        }

        @Test
        void verifieLexistanceDunUtilisateurAvaecExist(){
            //GIVEN


            Mockito.when(utilisateurRepository.findByIdUtilisateur(3L)).thenReturn(utilisateur);
            Mockito.when(mapUtilisateur.mapUtilisateurToDto(utilisateur)).thenReturn(utilisateurDTO);
            //THEN
            underTest.utilisateur(3L);
            Mockito.verify(mapUtilisateur).mapUtilisateurToDto(utilisateur);
        }
        @Test
        void retourneExeceptionSIlIdUtilisateurNexistPas(){

        }
    }