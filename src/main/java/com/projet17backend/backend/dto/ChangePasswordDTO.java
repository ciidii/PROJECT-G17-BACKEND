package com.projet17backend.backend.dto;

public class ChangePasswordDTO {
   private Long id;
   private String password;

    public ChangePasswordDTO(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public ChangePasswordDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
