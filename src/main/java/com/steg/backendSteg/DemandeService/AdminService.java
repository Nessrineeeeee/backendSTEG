package com.steg.backendSteg.DemandeService;

import com.steg.backendSteg.DemandeRepository.AgentRepository;
import com.steg.backendSteg.steg.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AgentRepository agentRepository;

    // Méthodes pour Agent
    public List<Agent> getAllAgent() {
        return agentRepository.findAll();
    }

    public Optional<Agent> getAgentById(String id) {
        return agentRepository.findById(id);
    }
    public Agent saveAgent(Agent agent) {
        return agentRepository.save(agent);
    }
    public Agent addAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    public Agent updateAgent(String id, Agent agent) {
        agent.setId(id);
        return agentRepository.save(agent);
    }


    public void deleteAgent(String id) {
        agentRepository.deleteById(id);
    }


    public List<Agent> searchByMatricule(String matricule) {
        // Utilisez la méthode findByMatricule du repository pour rechercher les agents par matricule
        return agentRepository.findByMatricule(matricule);
    }


}
