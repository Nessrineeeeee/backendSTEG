package com.steg.backendSteg.DemandeService;
import com.steg.backendSteg.DemandeRepository.AgentRepository;
import com.steg.backendSteg.steg.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
