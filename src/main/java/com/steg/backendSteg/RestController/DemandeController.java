package com.steg.backendSteg.RestController;

import com.steg.backendSteg.DemandeService.DemandeService; // Correction du chemin d'accès
import com.steg.backendSteg.steg.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.steg.backendSteg.steg.Demande;
import javax.mail.MessagingException;
import javax.management.relation.Relation;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/steg/")
public class DemandeController {
    private final DemandeService demandeService;

    @Autowired
    public DemandeController(DemandeService demandeService) {
        this.demandeService = demandeService;
    }
    @PostMapping("demande")
    public Demande ajouterdemande(@RequestBody Demande demande) {
        System.out.println("seccs");
        return demandeService.enregistrerDemande(demande);
    }

    @PostMapping("demande/{role}")
    public Demande ajouterDemande(@RequestBody Demande demande) {
        return demandeService.enregistrerDemande(demande); // Passez le rôle à la méthode de service
    }
    @GetMapping("demandes/{role}")
    public List<Demande> getDemandeByRole(@PathVariable("role") Agent.Role role) {
        return demandeService.getDemandeByRole(role);
    }
    @PutMapping("changeStatus")
    public Demande patchDemandeStatus(@RequestBody Demande demande, @RequestParam String status) {
        return demandeService.patchDemandeStatus(demande,status);
    }
    @GetMapping("get_by_id/{demandeId}")
    public Demande getDemandeById(@PathVariable("demandeId") String demandeId){
        return demandeService.getDemandeById(demandeId);
    }

}

