package com.steg.backendSteg.DemandeService; // Correction du package

import com.steg.backendSteg.DemandeRepository.AgentRepository;
import com.steg.backendSteg.steg.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.steg.backendSteg.DemandeRepository.DemandeRepository; // Correction du chemin d'accès
import com.steg.backendSteg.steg.Demande;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DemandeService {
    private final DemandeRepository demandeRepository;
    private final AgentRepository agentRepository;

    @Autowired
    public DemandeService(DemandeRepository demandeRepository, AgentRepository agentRepository) {
        this.demandeRepository = demandeRepository;
        this.agentRepository = agentRepository;
    }

    public Demande enregistrerDemande(Demande demande, String idClient) {
        demande.setDateDebut(LocalDateTime.now());
        Agent agent = agentRepository.findById(idClient)
                .orElseThrow(() -> new IllegalArgumentException("Agent not found with ID: " + idClient));
        List<Demande> agentDemand = agent.getDemandes();

        demande.setAgent(agent);
        demande = demandeRepository.save(demande);
        demande.setAgent(null);
        if (agentDemand == null) {
            agentDemand = new ArrayList<>();
        }
        agentDemand.add(demande);
        agent.setDemandes(agentDemand);
        agentRepository.save(agent);
        return demande;
    }

    public List<Demande> getDemandeByRole(Agent.Role role) {
        List<Demande> filteredDemandes = new ArrayList<>();
        List<Demande> allDemandes = demandeRepository.findAll();

        if (role.equals(Agent.Role.GUICHET)) {
            for (Demande demande : allDemandes) {
                if (demande.getStatus().equals(Demande.Status.EN_COURS_GU) || demande.getStatus().equals(Demande.Status.ACCEPTEE) || demande.getStatus().equals(Demande.Status.REFUSEE)) {
                    filteredDemandes.add(demande);
                }
            }
        }
        if (role.equals(Agent.Role.DPTE)) {
            for (Demande demande : allDemandes) {
                if (demande.getStatus() == Demande.Status.EN_COURS_DPTE) {
                    filteredDemandes.add(demande);
                }
            }
        }
        if (role.equals(Agent.Role.DDI)) {
            for (Demande demande : allDemandes) {
                if (demande.getStatus() == Demande.Status.EN_COURS_DDI) {
                    filteredDemandes.add(demande);
                }
            }
        }
        return filteredDemandes;
    }

    public Demande patchDemandeStatus(String idDemande, Demande demande) {
        Demande demandeToUpdate = demandeRepository.findById(idDemande)
                .orElseThrow(() -> new IllegalArgumentException("Demande not found with ID: " + idDemande));

        demandeToUpdate.setStatus(demande.getStatus());
        if(demande.getStatus().equals(Demande.Status.ACCEPTEE) || demande.getStatus().equals(Demande.Status.REFUSEE)){
            demandeToUpdate.setRapport(demande.getRapport());
        }
        return demandeRepository.save(demandeToUpdate);
    }


}
