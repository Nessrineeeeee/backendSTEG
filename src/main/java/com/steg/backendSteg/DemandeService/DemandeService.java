package com.steg.backendSteg.DemandeService;

import com.steg.backendSteg.DemandeRepository.AgentRepository;
import com.steg.backendSteg.steg.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.steg.backendSteg.DemandeRepository.DemandeRepository;
import com.steg.backendSteg.steg.Demande;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeService {
    private final DemandeRepository demandeRepository;
    private final AgentRepository agentRepository;
    private final NotificationService notificationService;


    @Autowired
    public DemandeService(DemandeRepository demandeRepository, AgentRepository agentRepository, NotificationService notificationService) {
        this.demandeRepository = demandeRepository;
        this.agentRepository = agentRepository;
        this.notificationService = notificationService;
    }

    public Demande enregistrerDemande(Demande demande) {
        demande.setDateDebut(LocalDateTime.now());
        Agent agent = null;
        List<Agent> agents = agentRepository.findAll();
        Demande finalDemande = demande;
        Optional<Agent> optionalAgent = agents.stream()
                .filter(a -> a.getEmail().equals(finalDemande.getEmail()))
                .findFirst();

        if (optionalAgent.isPresent()) {
            agent = optionalAgent.get();
        }



        List<Demande> agentDemand = agent.getDemandes();

        demande.setAgent(agent);
        demande.setStatus(Demande.Status.EN_COURS_GU);
        demande = demandeRepository.save(demande);
        demande.setAgent(null);
        if (agentDemand == null) {
            agentDemand = new ArrayList<>();
        }
        agentDemand.add(demande);
        agent.setDemandes(agentDemand);
        agentRepository.save(agent);
        // Envoyer un e-mail à la destination choisie (DDI ou DPTE) en fonction du rôle

        return demande;
    }
    private String getDestinataireEmail(Agent.Role role) {
        if (role.equals(Agent.Role.DPTE)) {
            return "benkileni.aziza@etudiant-fst.utm.tn";
        } else if (role.equals(Agent.Role.DDI)) {
            return "kacemnesrin@gmail.com";
        } else {
            throw new IllegalArgumentException("Role non pris en charge pour l'envoi d'e-mails");
        }
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

    public Demande patchDemandeStatus(Demande demande, String status) {
        Demande demandeToUpdate = demandeRepository.findById(demande.getId())
                .orElseThrow(() -> new IllegalArgumentException("Demande not found with ID: " + demande.getId()));
        demandeToUpdate.setStatus(Demande.Status.valueOf(status));
        notificationService.envoyerEmail("mail eli bich taba3tho","hello", "hello");
        return demandeRepository.save(demandeToUpdate);
    }
    public Demande getDemandeById(String demandeId) {
        return demandeRepository.findById(demandeId).orElseThrow(null);
    }

}