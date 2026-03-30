package com.darin.validateurpolitiquesacces.controleur;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ControleurSante {

    @GetMapping("/api/health")
    public Map<String, String> verifier() {
        return Map.of("statut", "OK", "message", "Application opérationnelle");
    }
}
