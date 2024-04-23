package com.projet17backend.backend.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class InfosPourBloquerUtilisateur implements Serializable {
    private Long idAdmin;
    private Long idUtilisateur;
    private LocalDateTime dateDeLeveeAutomatique;

    public InfosPourBloquerUtilisateur() {
    }

    public InfosPourBloquerUtilisateur(Long idAdmin, Long idUtilisateur, LocalDateTime dateDeLeveeAutomatique) {
        this.idAdmin = idAdmin;
        this.idUtilisateur = idUtilisateur;
        this.dateDeLeveeAutomatique = dateDeLeveeAutomatique;
    }

    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public LocalDateTime getDateDeLeveeAutomatique() {
        return dateDeLeveeAutomatique;
    }

    public void setDateDeLeveeAutomatique(LocalDateTime dateDeLeveeAutomatique) {
        this.dateDeLeveeAutomatique = dateDeLeveeAutomatique;
    }
}
