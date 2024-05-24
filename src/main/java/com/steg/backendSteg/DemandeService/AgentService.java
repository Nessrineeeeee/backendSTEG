package com.steg.backendSteg.DemandeService;
import com.steg.backendSteg.DemandeRepository.AgentRepository;
import com.steg.backendSteg.steg.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {
    private final AgentRepository agentRepository;

    @Autowired
    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent enregistreragant(Agent Agent) {
        return agentRepository.save(Agent);
    }
    public Agent authenticate(String email, String password) {
        List<Agent> agents = agentRepository.findAll();
        Optional<Agent> optionalAgent = agents.stream()
                .filter(a -> a.getEmail().equals(email))
                .findFirst();

        if (optionalAgent.isPresent()) {
            Agent agent = optionalAgent.get();
            if (agent.getPassword().equals(password)) {
                return agent;
            }
        }
        return null;
    }

}