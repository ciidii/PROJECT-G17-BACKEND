package com.projet17backend.backend.security;

import com.projet17backend.backend.services.UtilisateurService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {
    private final UtilisateurService utilisateurService;
    private final JwtService jwtService;

    public JwtFilter(UtilisateurService utilisateurService, JwtService jwtService) {
        this.utilisateurService = utilisateurService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token;
        String username = null;
        boolean isTokenExpered = true;
        //'Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MDAxNzI2MTYsImV4cCI6MTcwMDE3NTMxNiwic3ViIjoiRzE3R0IwMTUwIiwicHJlbm9tIjoiQm91YmFjYXIiLCJub20iOiJEaWFsbG8iLCJudW1lcm9UZWwiOiI3Nzc3Nzc3NzciLCJpZGVudGlmaWFudCI6IkcxN0dCMDE1MCIsInByZW1pZXJDb25uZXhpb24iOnRydWUsImFjdGl2YXRlZCI6dHJ1ZSwiZXN0QmxvcXVlciI6dHJ1ZSwicm9sZXMiOiJST0xFX1VUSUxJU0FURVVSIFJPTEVfQURNSU4gIiwiYWRyZXNzZSI6IlBpa2luZSIsImVtYWlsIjoiYm9vYmExNjc5NjdAZ21haWwuY29tIn0.FqJed9hzKMIi8jkwxYMtCATTUr4ozZsjKx6wRbV90ic'
        final String requestAuthorization = request.getHeader("Authorization");
        if (requestAuthorization != null && requestAuthorization.startsWith("Bearer ")) {
            token = requestAuthorization.substring(7);
            isTokenExpered = jwtService.isExperired(token);
            username = jwtService.extractUsername(token);
        }
        if (!isTokenExpered && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.utilisateurService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
