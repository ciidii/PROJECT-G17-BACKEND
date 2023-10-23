package com.projet17backend.backend.services.Impl;

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
    private UtilisateurService underTest;
    @BeforeEach
    void setUp(){
        underTest = new UtilisateurService(utilisateurRepository);
    }

    @Test
    void peutRetounerTousLesUtilisateur(){
        //WHEN
        underTest.utilisateurs();
        //THEN
        Mockito.verify(utilisateurRepository).findAll(Sort.by("idUtilisateur"));
    }
}