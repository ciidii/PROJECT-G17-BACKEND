package com.projet17backend.backend.security;

import com.projet17backend.backend.entities.Utilisateur;
import com.projet17backend.backend.services.UtilisateurService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final String ENCRYPTION_KEY = "6cd6dd0e5e4a433c31145f38e7d5c8bc9766e0a87cb9348f31227a04ff8f8025";
    private final UtilisateurService utilisateurService;

    public JwtService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public Map<String, String> generate(String username) {
        Utilisateur utilisateur = this.utilisateurService.trouverParIdentifiant(username);

        return this.generateJWT(utilisateur);
    }

    private Map<String, String> generateJWT(Utilisateur utilisateur) {
        final long currentTime = System.currentTimeMillis();
        final long expireTime = currentTime + 45 * 60 * 1000;
        Map<String, Object> claims = Map.of(
                "nom", utilisateur.getNom(),
                "prenom", utilisateur.getPrenom(),
                "email", utilisateur.getEmail(),
                "identifiant", utilisateur.getIdentifiant(),
                Claims.EXPIRATION, new Date(expireTime),
                Claims.SUBJECT, utilisateur.getIdentifiant()
        );
        String bearer = Jwts.builder()
                .issuedAt(new Date(currentTime))
                .expiration(new Date(expireTime))
                .subject(utilisateur.getIdentifiant())
                .claims(claims)
                .signWith(getKet(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of("bearer", bearer);
    }

    private Key getKet() {
        final byte[] key = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    public String extractUsername(String token) {
        return this.getClaims(token, Claims::getSubject);
    }

    public boolean isExperired(String token) {
        Date expirationDate = this.getClaims(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    private <T> T getClaims(String token, Function<Claims, T> fonction) {
        Claims claims = getAllClaims(token);
        return fonction.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(this.getKet())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
