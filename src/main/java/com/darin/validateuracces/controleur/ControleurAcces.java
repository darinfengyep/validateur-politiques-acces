package com.darin.validateuracces.controleur;

import com.darin.validateuracces.dto.DemandeAcces;
import com.darin.validateuracces.dto.ReponseValidation;
import com.darin.validateuracces.service.ServiceValidationAcces;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ControleurAcces {
    private final ServiceValidationAcces serviceValidationAcces;

    public ControleurAcces(ServiceValidationAcces serviceValidationAcces) {
        this.serviceValidationAcces = serviceValidationAcces;
    }

    @GetMapping("/sante")
    public Map<String, String> verifierSante() {
        return Map.of("statut", "OK", "message", "L'API fonctionne correctement.");
    }

    @GetMapping("/acces/regles")
    public List<String> obtenirRegles() {
        return serviceValidationAcces.listerRegles();
    }

    @PostMapping("/acces/valider")
    public ResponseEntity<ReponseValidation> validerDemande(@Valid @RequestBody DemandeAcces demandeAcces) {
        return ResponseEntity.ok(serviceValidationAcces.valider(demandeAcces));
    }
}
