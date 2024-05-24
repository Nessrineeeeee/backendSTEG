package com.steg.backendSteg.DemandeService;
import com.steg.backendSteg.DemandeRepository.AgentRepository;
import com.steg.backendSteg.steg.Agent;
import com.steg.backendSteg.steg.Detaille;
import org.springframework.beans.factory.annotation.Autowired;
import com.steg.backendSteg.DemandeRepository.DetailleRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class DetailleService {
    private final DetailleRepository detailleRepository;
    private final AgentRepository agentRepository;


    @Autowired
    public DetailleService(DetailleRepository detailleRepository, AgentRepository agentRepository) {
        this.detailleRepository = detailleRepository;
        this.agentRepository = agentRepository;
    }

    public Detaille enregistrerDetaille(Detaille detaille) {
        detaille.setDateDebut(LocalDateTime.now());
        Agent agent = null;
        List<Agent> agents = agentRepository.findAll();
        Detaille finalDetaille = detaille;
        Optional<Agent> optionalAgent = agents.stream()
                .filter(a -> a.getEmail().equals(finalDetaille.getEmail()))
                .findFirst();

        if (optionalAgent.isPresent()) {
            agent = optionalAgent.get();
        }



        List<Detaille> agentDetaille = agent.getDetaille();

        detaille.setAgent(agent);
        detaille = detailleRepository.save(detaille);
        detaille.setAgent(null);
        if (agentDetaille == null) {
            agentDetaille = new ArrayList<>();
        }
        agentDetaille.add(detaille);
        agent.setDetaille(agentDetaille);
        agentRepository.save(agent);
        return detaille;
    }

    public List<Detaille> getDetailleByRole(Agent.Role role) {
        List<Detaille> filteredDetaille = new ArrayList<>();
        List<Detaille> allDetaille = detailleRepository.findAll();

        if (role.equals(Agent.Role.GUICHET)) {
            for (Detaille detaille : allDetaille) {
                if (detaille.getStatus().equals(Detaille.Status.EN_COURS_GU) || detaille.getStatus().equals(Detaille.Status.ACCEPTEE) || detaille.getStatus().equals(Detaille.Status.REFUSEE)) {
                    filteredDetaille.add(detaille);
                }
            }
        }
        if (role.equals(Agent.Role.DPTE)) {
            for (Detaille detaille : allDetaille) {
                if (detaille.getStatus() == Detaille.Status.EN_COURS_DPTE) {
                    filteredDetaille.add(detaille);
                }
            }
        }
        if (role.equals(Agent.Role.DDI)) {
            for (Detaille detaille : allDetaille) {
                if (detaille.getStatus() == Detaille.Status.EN_COURS_DDI) {
                    filteredDetaille.add(detaille);
                }
            }
        }
        return filteredDetaille;
    }

    public Detaille patchDetailleStatus(String idDetaille, Detaille detaille) {
        Detaille detailleToUpdate = detailleRepository.findById(idDetaille)
                .orElseThrow(() -> new IllegalArgumentException("Demande not found with ID: " + idDetaille));

        detailleToUpdate.setStatus(detaille.getStatus());
        if(detaille.getStatus().equals(Detaille.Status.ACCEPTEE) || detaille.getStatus().equals(Detaille.Status.REFUSEE)){
            detailleToUpdate.setRapport(detaille.getRapport());
        }
        return detailleRepository.save(detailleToUpdate);
    }


}
