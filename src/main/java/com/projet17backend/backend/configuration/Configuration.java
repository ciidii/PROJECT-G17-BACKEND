package com.projet17backend.backend.configuration;

import java.security.SecureRandom;
import java.util.Random;

public class Configuration {

        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";

        private static final int DEFAULT_PASSWORD_LENGTH = 8;

        public static String generateRandomPassword() {
            return generateRandomPassword(DEFAULT_PASSWORD_LENGTH);
        }

        public static String generateRandomPassword(int length) {
            if (length <= 8) {
                throw new IllegalArgumentException("mot de passe trop court.longueur minimal 8");
            }
            SecureRandom random = new SecureRandom();
            StringBuilder password = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(CHARACTERS.length());
                char randomChar = CHARACTERS.charAt(randomIndex);
                password.append(randomChar);
            }

            return password.toString();
        }

    private static String genereIdentifiant() {
        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        return String.format("G17GB%04d", randomNumber);
    }

}
