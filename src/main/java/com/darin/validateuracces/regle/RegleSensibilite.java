package com.darin.validateuracces.regle;

import com.darin.validateuracces.dto.DemandeAcces;
import com.darin.validateuracces.dto.ResultatRegle;
import org.springframework.stereotype.Component;

@Component
public class RegleSensibilite implements RegleValidationAcces {
    @Override
    public ResultatRegle evaluer(DemandeAcces demandeAcces) {
        if ("ELEVEE".equalsIgnoreCase(demandeAcces.getNiveauSensibilite()) || "HAUTE".equalsIgnoreCase(demandeAcces.getNiveauSensibilite())) {
            return new ResultatRegle(obtenirNomRegle(), "ATTENTE", "Un accès sensible nécessite une validation managériale.");
        }
        return new ResultatRegle(obtenirNomRegle(), "OK", "Le niveau de sensibilité ne nécessite pas d'escalade.");
    }
    @Override
    public String obtenirNomRegle() { return "Contrôle de sensibilité"; }
}
