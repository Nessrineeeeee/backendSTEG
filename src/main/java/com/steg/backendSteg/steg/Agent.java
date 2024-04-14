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
    private String _id;
    private String matriculeFiscal;
    private String prenom;
    private String nom;
    private String adresse;
    private String email;
    private String telephone;
    private String motDePasse;
    private Role role;
    private List<Demande> demandes;
    public enum Role {
        CLIENT,
        GUICHET,
        DPTE,
        DDI
    }
}


