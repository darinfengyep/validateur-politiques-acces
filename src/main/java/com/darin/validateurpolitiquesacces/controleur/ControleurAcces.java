package com.darin.validateurpolitiquesacces.controleur;

import com.darin.validateurpolitiquesacces.dto.ReponseTableauBord;
import com.darin.validateurpolitiquesacces.dto.ReponseValidationAcces;
import com.darin.validateurpolitiquesacces.dto.RequeteDecisionManuelle;
import com.darin.validateurpolitiquesacces.dto.RequeteValidationAcces;
import com.darin.validateurpolitiquesacces.entite.DemandeAcces;
import com.darin.validateurpolitiquesacces.entite.UtilisateurHabilite;
import com.darin.validateurpolitiquesacces.service.ServiceValidationAcces;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acces")
@CrossOrigin(origins = "*")
public class ControleurAcces {

    private final ServiceValidationAcces serviceValidationAcces;

    public ControleurAcces(ServiceValidationAcces serviceValidationAcces) {
        this.serviceValidationAcces = serviceValidationAcces;
    }

    @PostMapping("/valider")
    public ResponseEntity<ReponseValidationAcces> valider(@Valid @RequestBody RequeteValidationAcces requete) {
        return ResponseEntity.ok(serviceValidationAcces.validerDemande(requete));
    }

    @PostMapping("/decider/{id}")
    public ResponseEntity<DemandeAcces> deciderManuellement(@PathVariable Long id,
                                                            @Valid @RequestBody RequeteDecisionManuelle requete,
                                                            Authentication authentication) {
        String validePar = authentication != null ? authentication.getName() : "inconnu";
        return ResponseEntity.ok(serviceValidationAcces.deciderManuellement(id, requete, validePar));
    }

    @GetMapping("/historique")
    public ResponseEntity<List<DemandeAcces>> historique() {
        return ResponseEntity.ok(serviceValidationAcces.recupererHistorique());
    }

    @GetMapping("/utilisateurs")
    public ResponseEntity<List<UtilisateurHabilite>> utilisateurs() {
        return ResponseEntity.ok(serviceValidationAcces.recupererTousLesUtilisateurs());
    }

    @GetMapping("/regles")
    public ResponseEntity<List<String>> regles() {
        return ResponseEntity.ok(serviceValidationAcces.recupererRegles());
    }

    @GetMapping("/tableau-bord")
    public ResponseEntity<ReponseTableauBord> tableauBord() {
        return ResponseEntity.ok(serviceValidationAcces.recupererTableauBord());
    }
}
