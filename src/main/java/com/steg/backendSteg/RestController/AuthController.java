package com.steg.backendSteg.RestController;

import com.steg.backendSteg.DemandeService.AgentService;
import com.steg.backendSteg.steg.Agent;
import com.steg.backendSteg.steg.Authmodel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/steg/")
@RestController
public class AuthController {

    private final AgentService agentService;

    public AuthController(AgentService agentService) {

        this.agentService = agentService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Authmodel request) {
        Agent agent = agentService.authenticate(request.getEmail(), request.getPassword());
        if (agent == null) {
            return ResponseEntity.ok(null);
        } else
            return ResponseEntity.ok(agent);
    }

}
