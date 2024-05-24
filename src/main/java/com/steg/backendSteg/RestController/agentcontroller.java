package com.steg.backendSteg.RestController;
import com.steg.backendSteg.DemandeService.AgentService; // Correction du chemin d'accès
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.steg.backendSteg.steg.Agent;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/steg/")
public class agentcontroller {
    private final AgentService agentService;
    @Autowired
    public agentcontroller(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("agent")
    public Agent ajouterDemande(@RequestBody Agent agent) {
        System.out.println("seccs");
        return agentService.enregistreragant(agent);
    }

}
