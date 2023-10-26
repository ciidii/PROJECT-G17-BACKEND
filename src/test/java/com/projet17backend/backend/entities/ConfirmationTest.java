package com.projet17backend.backend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfirmationTest {

    private Confirmation confirmation;

    @BeforeEach
    public void setUp() {
        Utilisateur utilisateur = new Utilisateur();
        LocalDateTime testDate = LocalDateTime.now();
        String testToken = UUID.randomUUID().toString();
        confirmation = new Confirmation(utilisateur);
        confirmation.setCreatedDate(testDate);
        confirmation.setToken(testToken);
    }

    @Test
    public void testGetId() {
        Long expectedId = confirmation.getId();
        assertEquals(expectedId, confirmation.getId());
    }

    @Test
    public void testSetId() {
        Long newId = 12345L;
        confirmation.setId(newId);
        assertEquals(newId, confirmation.getId());
    }

    @Test
    public void testGetToken() {
        String expectedToken = confirmation.getToken();
        assertEquals(expectedToken, confirmation.getToken());
    }

    @Test
    public void testSetToken() {
        String newToken = "newToken";
        confirmation.setToken(newToken);
        assertEquals(newToken, confirmation.getToken());
    }

    @Test
    public void testGetCreatedDate() {
        LocalDateTime expectedDate = confirmation.getCreatedDate();
        assertEquals(expectedDate, confirmation.getCreatedDate());
    }

    @Test
    public void testSetCreatedDate() {
        LocalDateTime newDate = LocalDateTime.now().minusDays(1);
        confirmation.setCreatedDate(newDate);
        assertEquals(newDate, confirmation.getCreatedDate());
    }

    @Test
    public void testGetUtilisateur() {
        Utilisateur expectedUtilisateur = confirmation.getUtilisateur();
        assertEquals(expectedUtilisateur, confirmation.getUtilisateur());
    }

    @Test
    public void testSetUtilisateur() {
        Utilisateur newUtilisateur = new Utilisateur();
        confirmation.setUtilisateur(newUtilisateur);
        assertEquals(newUtilisateur, confirmation.getUtilisateur());
    }
}
