package com.steg.backendSteg.steg;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "Agents")
public class Agent {
    @Id
    private String id;
    private String matriculefiscal;
    private String firstName;
    private String adresse;
    private String email;
    private String phoneNumber;
    private String password;
    private Role role;
    private List<Demande> demandes;
    private List<Detaille> detaille;
    private String matricule;
    private String nom;
    private String address;
    private String prenom;
    private String telephone;

    public enum Role {
        CLIENT,
        GUICHET,
        DPTE,
        DDI,
        ADMIN
    }
}

