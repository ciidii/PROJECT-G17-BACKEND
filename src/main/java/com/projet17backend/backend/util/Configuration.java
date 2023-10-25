package com.projet17backend.backend.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Configuration {

        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
        private static Random secureRandom;

    static {
        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static final int DEFAULT_PASSWORD_LENGTH = 8;

    public Configuration() throws NoSuchAlgorithmException {
    }

    public static String genereMotDePass() {
            if (Configuration.DEFAULT_PASSWORD_LENGTH < 8) {
                throw new IllegalArgumentException("mot de passe trop court.longueur minimal 8");
            }
            StringBuilder password = new StringBuilder();
            for (int i = 0; i < Configuration.DEFAULT_PASSWORD_LENGTH; i++) {
                int randomIndex = secureRandom.nextInt(CHARACTERS.length());
                char randomChar = CHARACTERS.charAt(randomIndex);
                password.append(randomChar);
            }

            return password.toString();
        }

    public static String genereIdentifiant() {
        int randomNumber = secureRandom.nextInt(10000);
        return String.format("G17GB%04d", randomNumber);
    }

}
