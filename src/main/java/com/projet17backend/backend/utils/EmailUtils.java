package com.projet17backend.backend.utils;

import org.springframework.beans.factory.annotation.Value;

public class EmailUtils {
    @Value("${MAIN_PATH}")
    private static String path;
    public static String getEmailMessage(String name, String modPass,String identifiant, String host, String token,Long idUtilisateur){

        return "Salut "+name+"\n\n,Votre compte est créer avec succée" +
                "\n\nVoici vos identifiants\n\nIdentifiant:" +identifiant+"\n\nMot de passe: "+modPass+
                "\n\nActivé votre compte\n\n"+getVerificationUrl(host,token,idUtilisateur)+"\n\nAssistant Techniques G17GB";
    }
    private static String getVerificationUrl(String host, String token,Long idUtilisateur) {
        return host+"/backend"+"/utilisateurs?utilisateur="+idUtilisateur+"&token="+token;
    }
}
