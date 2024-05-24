package com.steg.backendSteg.RestController;
import com.steg.backendSteg.DemandeService.DetailleService;
import com.steg.backendSteg.steg.Agent;
import com.steg.backendSteg.steg.Detaille;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.steg.backendSteg.steg.Demande;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/steg/")
public class DetailleController {
    private final DetailleService detailleService;

    @Autowired
    public DetailleController(DetailleService detailleService) {
        this.detailleService = detailleService;
    }
    @PostMapping("detaille")
    public Detaille ajouterdetaille(@RequestBody Detaille detaille) {
        System.out.println("seccs");
        return detailleService.enregistrerDetaille(detaille);
    }
    @GetMapping("detaille/{role}")
    public List<Detaille> getDetailleByRole(@PathVariable Agent.Role role) {
        return detailleService.getDetailleByRole(role);
    }
    @PatchMapping("changeStatus/{idDetaille}")
    public Detaille patchDetailleStatus(@PathVariable String idDetaille, @RequestBody Detaille detaille) {
        return detailleService.patchDetailleStatus(idDetaille,detaille);
    }
}
