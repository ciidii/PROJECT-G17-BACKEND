package com.projet17backend.backend.utils;

import org.springframework.beans.factory.annotation.Value;

public class EmailUtils {
    @Value("${MAIN_PATH}")
    private static String path;
    public static String getEmailMessage(String name, String host, String token){

        return "Salut "+name+"\n\n,Votre compte est créer avec succée" +
                " Cliquez sur le lien ci-dessous pour activer votre compte" +
                "Activé votre compte\n\n"+getVerificationUrl(host,token)+"\n\nAssistant Techniques G17GB";
    }
    private static String getVerificationUrl(String host, String token) {
        return host+"/backend"+"/utilisateurs?token="+token;
    }
}
