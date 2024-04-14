package com.steg.backendSteg.steg;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "demandes")
public class Demande {

    @Id
    private String id;
    private String matriculeFiscale;
    private double paiement;
    private LocalDateTime dateDebut;
    private String document;
    private TypeDemande typeDemande;
    private Status status;
    private Agent agent;
    private String rapport;


    public enum TypeDemande {
        DEMANDE_PRELIMINAIRE,
        DEMANDE_DETAILLEE
    }
    public enum Status {
        EN_COURS_GU,
        EN_COURS_DDI,
        EN_COURS_DPTE,
        REFUSEE,
        ACCEPTEE,
    }

}

