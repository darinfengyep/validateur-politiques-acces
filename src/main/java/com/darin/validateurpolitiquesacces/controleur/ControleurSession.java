
package com.darin.validateurpolitiquesacces.controleur;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ControleurSession {

    @GetMapping("/api/session")
    public Map<String, Object> session(Authentication authentication) {
        Map<String, Object> resultat = new LinkedHashMap<>();
        resultat.put("utilisateur", authentication.getName());
        resultat.put("roles", authentication.getAuthorities().stream().map(a -> a.getAuthority()).toList());
        return resultat;
    }
}
