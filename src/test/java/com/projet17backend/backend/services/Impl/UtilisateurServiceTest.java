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
        private UtilisateurRepository utilisateurRepositoryMock;
        @Mock
        private MapUtilisateur mapUtilisateurMock;
        @Mock
        private Utilisateur utilisateurMock;
        private UtilisateurServiceImpl underTest;
        private Utilisateur utilisateur;
        private UtilisateurDTO utilisateurDTO;
        private List<Utilisateur> utilisateurs;
        @BeforeEach
        void setUp(){
            underTest = new UtilisateurServiceImpl(utilisateurRepositoryMock, mapUtilisateurMock,utilisateurMock);
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
            Mockito.verify(utilisateurRepositoryMock).findAll(Sort.by("idUtilisateur"));
        }

        @Test
        void verifieLexistanceDunUtilisateurAvaecExist(){
            //GIVEN
            Mockito.when(utilisateurRepositoryMock.findByIdUtilisateur(3L)).thenReturn(utilisateur);
            Mockito.when(mapUtilisateurMock.mapUtilisateurToDto(utilisateur)).thenReturn(utilisateurDTO);
            //THEN
            underTest.utilisateur(3L);
            Mockito.verify(mapUtilisateurMock).mapUtilisateurToDto(utilisateur);
        }
        @Disabled
        @Test
        void retourneExeceptionSIlIdUtilisateurNexistPas(){

        }
        @Test
        void testSIOnPeutAjouterUtilisateur(){
            //GIVEN

            //WHEN
            Mockito.when(mapUtilisateurMock.mapDtoToUlisateur(utilisateurDTO)).thenReturn(utilisateur);
            Mockito.when(mapUtilisateurMock.mapUtilisateurToDto(utilisateur)).thenReturn(utilisateurDTO);
            underTest.ajouter(utilisateurDTO);
            // THEN
            ArgumentCaptor<Utilisateur> utilisateurArgumentCaptor = ArgumentCaptor.
                    forClass(Utilisateur.class);
            Mockito.verify(utilisateurRepositoryMock).save(utilisateurArgumentCaptor.capture());
            Utilisateur utilisateurCapturee =    utilisateurArgumentCaptor.getValue();
            Assertions.assertThat(mapUtilisateurMock.mapUtilisateurToDto(utilisateurCapturee)).isEqualTo(utilisateurDTO);
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
            Mockito.when(mapUtilisateurMock.mapDtoToUlisateur(utilisateurDTO)).thenReturn(utilisateur);
            Mockito.when(mapUtilisateurMock.mapUtilisateurToDto(utilisateur)).thenReturn(utilisateurDTO);
            BDDMockito.given(utilisateurRepositoryMock.findByEmail(utilisateurDTO.getEmail()).isPresent()).willReturn(true);
            underTest.ajouter(utilisateurDTO1);
            // THEN
        }
        @Test
        void doitAfficherLaListeDesUtilisateur(){
            //GIVEN
            //WHEN
            BDDMockito.given(utilisateurRepositoryMock.findAll(Sort.by("idUtilisateur"))).willReturn(utilisateurs);
            List<UtilisateurDTO> utilisateursRetouner =  underTest.utilisateurs();
            //THEN
            Mockito.verify(utilisateurRepositoryMock).findAll(Sort.by("idUtilisateur"));
            Assertions.assertThat(utilisateursRetouner.size()).isEqualTo(2);

        }
        @Test
        void retounUnUtilisateurSiSonExist(){
            BDDMockito.given(utilisateurRepositoryMock.findByIdUtilisateur(3L)).willReturn(utilisateur);
            underTest.utilisateur(3L);
            Mockito.verify(utilisateurRepositoryMock,Mockito.times(2)).findByIdUtilisateur(3L);
        }
        @Test
        void lancerUnExectionSiIdUtilisateurNexistPas(){

            Assertions.assertThatThrownBy(()->underTest.utilisateur(3L)
            ).isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("Utilisateur n'existe pas");
        }
        @Disabled
        @Test
        void TestSiLesInformationUtilisateurSonModifier() {
            Utilisateur utilisateur =   new Utilisateur(
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
            // GIVEN
            Long userId = 3L;
            BDDMockito.given(utilisateurRepositoryMock.findByIdUtilisateur(userId)).willReturn(utilisateur);
            BDDMockito.given(mapUtilisateurMock.mapDtoToUlisateur(utilisateurDTO)).willReturn(utilisateur);
            BDDMockito.given(utilisateurMock.getIdUtilisateur()).willReturn(userId);

            // WHEN
            underTest.modifierMesInfos(userId, utilisateurDTO);

            // THEN
            // Assurez-vous que la méthode findByIdUtilisateur a été appelée avec le bon ID utilisateur
            Mockito.verify(utilisateurRepositoryMock).findByIdUtilisateur(userId);
        }
    }