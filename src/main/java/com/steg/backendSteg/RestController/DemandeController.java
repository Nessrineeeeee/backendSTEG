package com.steg.backendSteg.RestController;

import com.steg.backendSteg.DemandeService.DemandeService; // Correction du chemin d'accès
import com.steg.backendSteg.steg.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.steg.backendSteg.steg.Demande;

import java.util.List;

@RestController
@RequestMapping("/steg/")
public class DemandeController {
    private final DemandeService demandeService;

    @Autowired
    public DemandeController(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @PostMapping("demande/{idClient}")
    public Demande ajouterDemande(@RequestBody Demande demande,@PathVariable String idClient) {
        return demandeService.enregistrerDemande(demande, idClient);
    }
    @GetMapping("demandes/{role}")
    public List<Demande> getDemandeByRole(@PathVariable Agent.Role role) {
        return demandeService.getDemandeByRole(role);
    }
    @PatchMapping("changeStatus/{idDemande}")
    public Demande patchDemandeStatus(@PathVariable String idDemande, @RequestBody Demande demande) {
        return demandeService.patchDemandeStatus(idDemande,demande);
    }
}
