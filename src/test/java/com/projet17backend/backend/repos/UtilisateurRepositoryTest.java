package com.projet17backend.backend.repos;

import com.projet17backend.backend.entities.ROLE;
import com.projet17backend.backend.entities.Utilisateur;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UtilisateurRepositoryTest {
    @Autowired
    private UtilisateurRepository underTest;

    @BeforeEach
    void setUp(){
     Utilisateur utilisateur =    new Utilisateur(
                3L,
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
     Utilisateur utilisateur1 = new Utilisateur(
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
        underTest.save(utilisateur);
        underTest.save(utilisateur1);

    }
    @AfterEach
    void reset(){
        underTest.deleteAll();
    }
    @Test
    @Disabled
    void doitVerifierSiUnUtilisateurExistParSonId(){
        //GIVEN

        //WHEN
        Utilisateur utilisateurFronDB = underTest.findByIdUtilisateur(1L);

        //THEN
        Assertions.assertThat(utilisateurFronDB.getIdUtilisateur()).isEqualTo(1L);
    }

    @Test
     void doitTrouverUnUtilisateurParEmail(){
        //WHEN
        Utilisateur utilisateurFromDB = underTest.findByEmail("boubacarcidi77@gmail.com").orElseThrow();
        //THEN
        Assertions.assertThat(utilisateurFromDB.getEmail()).isEqualTo("boubacarcidi77@gmail.com");
    }
    @Test
    void doitTrouverUtilisateurParSonIdentfiant(){
        //WHEN
        Utilisateur utilisateurFromDB  = underTest.findByIdentifiant("G17GB001").orElseThrow();
        //THEN
        Assertions.assertThat(utilisateurFromDB.getIdentifiant()).isEqualTo("G17GB001");
    }
    @Test
    void doitRetournerToutLesUtilisateurs(){
        //GEVEN

        //WHEN
        List<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurs = underTest.findAll(Sort.by("idUtilisateur"));
        //THEN
        Assertions.assertThat(utilisateurs.size()).isEqualTo(2);

    }
}