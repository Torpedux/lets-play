package com.letsplay.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotBlank(message = "Le nom est obligatoire")
    private String name;

    @NotBlank
    @Email(message = "Format d'email invalide")
    private String email;

    @NotBlank
    @JsonIgnore // Sécurité : ne jamais renvoyer le mot de passe
    private String password;

    private String role; // "ROLE_USER" ou "ROLE_ADMIN"
}