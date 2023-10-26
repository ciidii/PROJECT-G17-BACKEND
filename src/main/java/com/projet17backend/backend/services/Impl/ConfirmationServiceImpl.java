package com.projet17backend.backend.services.Impl;

import com.projet17backend.backend.repos.ConfirmationRepository;
import com.projet17backend.backend.services.ConfirmationService;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationServiceImpl implements ConfirmationService {
    private final ConfirmationRepository confirmationRepository;

    public ConfirmationServiceImpl(ConfirmationRepository confirmationRepository) {
        this.confirmationRepository = confirmationRepository;
    }

    @Override
    public Boolean verifieConfirmationParSonToken(String token) {
        return confirmationRepository.existsByToken(token);
    }
}
