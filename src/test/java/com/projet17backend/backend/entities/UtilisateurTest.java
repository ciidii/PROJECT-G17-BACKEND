package com.projet17backend.backend.entities;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UtilisateurTest {

    private Utilisateur utilisateur;

    @Before
    public void setUp() {
        utilisateur = new Utilisateur();
    }

    @Test
    public void testIdUtilisateur() {
        utilisateur.setIdUtilisateur(1L);
        assertEquals(1L, utilisateur.getIdUtilisateur().longValue());
    }

    @Test
    public void testNom() {
        utilisateur.setNom("John");
        assertEquals("John", utilisateur.getNom());
    }

    @Test
    public void testPrenom() {
        utilisateur.setPrenom("Doe");
        assertEquals("Doe", utilisateur.getPrenom());
    }

    @Test
    public void testNumeroTel() {
        utilisateur.setNumeroTel("123456789");
        assertEquals("123456789", utilisateur.getNumeroTel());
    }

    @Test
    public void testEmail() {
        utilisateur.setEmail("john@example.com");
        assertEquals("john@example.com", utilisateur.getEmail());
    }

    @Test
    public void testIdentifiant() {
        utilisateur.setIdentifiant("johndoe");
        assertEquals("johndoe", utilisateur.getIdentifiant());
    }

    @Test
    public void testMotDePasse() {
        utilisateur.setMotDePasse("password");
        assertEquals("password", utilisateur.getMotDePasse());
    }

    @Test
    public void testAdresse() {
        utilisateur.setAdresse("123 Main St");
        assertEquals("123 Main St", utilisateur.getAdresse());
    }

    @Test
    public void testRole() {
        utilisateur.setRole(ROLE.ROLE_ADMIN);
        assertEquals(ROLE.ROLE_ADMIN, utilisateur.getRole());
    }

    @Test
    public void testActivated() {
        utilisateur.setActivated(true);
        assertEquals(true, utilisateur.getActivated());
    }

    @Test
    public void testPremierConnexion() {
        utilisateur.setPremierConnexion(false);
        assertEquals(false, utilisateur.isPremierConnexion());
    }
}
