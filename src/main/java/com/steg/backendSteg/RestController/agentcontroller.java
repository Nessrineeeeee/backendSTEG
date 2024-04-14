package com.steg.backendSteg.RestController;
import com.steg.backendSteg.DemandeService.AgentService; // Correction du chemin d'accès
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.steg.backendSteg.steg.Agent;
@RestController
@RequestMapping("/steg/")
public class agentcontroller {
    private final AgentService agentService;
    @Autowired
    public agentcontroller(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/agent")
    public Agent ajouterDemande(@RequestBody Agent agent) {
        return agentService.enregistreragant(agent);
    }

}
