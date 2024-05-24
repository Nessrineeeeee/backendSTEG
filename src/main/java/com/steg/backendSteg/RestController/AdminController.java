package com.steg.backendSteg.RestController;

import com.steg.backendSteg.DemandeService.AdminService;
import com.steg.backendSteg.steg.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Endpoints pour Agent
    @GetMapping("/allagent")
    public List<Agent> getAllAgent() {
        return adminService.getAllAgent();
    }

    @GetMapping("/agent/{id}")
    public Optional<Agent> getAgentById(@PathVariable String id) {
        return adminService.getAgentById(id);
    }


    @PostMapping("/addagent")
    public ResponseEntity<Agent> addAgent(@RequestBody Agent agent) {
        Agent savedAgent = adminService.saveAgent(agent);
        return new ResponseEntity<>(savedAgent, HttpStatus.CREATED);
    }




    @PutMapping("/putagent/{id}")
    public Agent updateAgent(@PathVariable String id, @RequestBody Agent agent) {
        return adminService.updateAgent(id, agent);
    }

    @DeleteMapping("/dagent/{id}")
    public void deleteAgent(@PathVariable String id) {
        adminService.deleteAgent(id);
    }

    @GetMapping("/search")
    public List<Agent> searchByMatricule(@RequestParam String matricule) {
        return adminService.searchByMatricule(matricule);
    }



}
