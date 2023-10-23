    package com.projet17backend.backend.services.Impl;

    import com.projet17backend.backend.dto.UtilisateurDTO;
    import com.projet17backend.backend.entities.ROLE;
    import com.projet17backend.backend.entities.Utilisateur;
    import com.projet17backend.backend.mapper.MapUtilisateur;
    import com.projet17backend.backend.repos.UtilisateurRepository;
    import org.assertj.core.api.Assertions;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Disabled;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.ArgumentCaptor;
    import org.mockito.BDDMockito;
    import org.mockito.Mock;
    import org.mockito.Mockito;
    import org.mockito.junit.jupiter.MockitoExtension;
    import org.springframework.data.domain.Sort;

    import java.util.ArrayList;
    import java.util.List;

    @ExtendWith(MockitoExtension.class)
    class UtilisateurServiceTest {
        @Mock
        private UtilisateurRepository utilisateurRepository;
        @Mock
        private MapUtilisateur mapUtilisateur;
        private UtilisateurService underTest;
        private Utilisateur utilisateur;
        private UtilisateurDTO utilisateurDTO;
        private List<Utilisateur> utilisateurs;
        @BeforeEach
        void setUp(){
            underTest = new UtilisateurService(utilisateurRepository,mapUtilisateur);
            utilisateurs  = new ArrayList<>();
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
             utilisateurs.add(utilisateur);
             utilisateurs.add(new Utilisateur(
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
             ));
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
        @Disabled
        @Test
        void retourneExeceptionSIlIdUtilisateurNexistPas(){

        }
        @Test
        void testSIOnPeutAjouterUtilisateur(){
            //GIVEN

            //WHEN
            Mockito.when(mapUtilisateur.mapDtoToUlisateur(utilisateurDTO)).thenReturn(utilisateur);
            Mockito.when(mapUtilisateur.mapUtilisateurToDto(utilisateur)).thenReturn(utilisateurDTO);
            underTest.ajouter(utilisateurDTO);
            // THEN
            ArgumentCaptor<Utilisateur> utilisateurArgumentCaptor = ArgumentCaptor.
                    forClass(Utilisateur.class);
            Mockito.verify(utilisateurRepository).save(utilisateurArgumentCaptor.capture());
            Utilisateur utilisateurCapturee =    utilisateurArgumentCaptor.getValue();
            Assertions.assertThat(mapUtilisateur.mapUtilisateurToDto(utilisateurCapturee)).isEqualTo(utilisateurDTO);
        }
        @Test
        @Disabled
        void testSIOnPeutAjouterUtilisateurCarExistDeja(){
            //GIVEN
            UtilisateurDTO utilisateurDTO1 = new UtilisateurDTO(
                    null,
                    "Diallo",
                    "Boubacar",
                    "782156437",
                    "boubacarcidi77@gmail.com",
                    "G17GB002",
                    "passer",
                    "Malika",
                    ROLE.ROLE_ADMIN,
                    false,
                    true
            );
            //WHEN
            Mockito.when(mapUtilisateur.mapDtoToUlisateur(utilisateurDTO)).thenReturn(utilisateur);
            Mockito.when(mapUtilisateur.mapUtilisateurToDto(utilisateur)).thenReturn(utilisateurDTO);
            BDDMockito.given(utilisateurRepository.findByEmail(utilisateurDTO.getEmail()).isPresent()).willReturn(true);
            underTest.ajouter(utilisateurDTO1);
            // THEN
        }
        @Test
        void doitAfficherLaListeDesUtilisateur(){
            //GIVEN
            //WHEN
            BDDMockito.given(utilisateurRepository.findAll(Sort.by("idUtilisateur"))).willReturn(utilisateurs);
            List<UtilisateurDTO> utilisateursRetouner =  underTest.utilisateurs();
            //THEN
            Mockito.verify(utilisateurRepository).findAll(Sort.by("idUtilisateur"));
            Assertions.assertThat(utilisateursRetouner.size()).isEqualTo(2);

        }
        @Test
        void retounUnUtilisateurSiSonExist(){
            BDDMockito.given(utilisateurRepository.findByIdUtilisateur(3L)).willReturn(utilisateur);
            underTest.utilisateur(3L);
            Mockito.verify(utilisateurRepository,Mockito.times(2)).findByIdUtilisateur(3L);
        }
        @Test
        void lancerUnExectionSiIdUtilisateurNexistPas(){

            Assertions.assertThatThrownBy(()->underTest.utilisateur(3L)
            ).isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("Utilisateur n'existe pas");
        }
    }