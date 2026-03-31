package com.darin.validateuracces.regle;

import com.darin.validateuracces.dto.DemandeAcces;
import com.darin.validateuracces.dto.ResultatRegle;
import org.springframework.stereotype.Component;

@Component
public class RegleJustification implements RegleValidationAcces {
    @Override
    public ResultatRegle evaluer(DemandeAcces demandeAcces) {
        String justification = demandeAcces.getJustification() == null ? "" : demandeAcces.getJustification().trim();
        if (justification.length() < 10) {
            return new ResultatRegle(obtenirNomRegle(), "REJET", "La justification est insuffisante.");
        }
        return new ResultatRegle(obtenirNomRegle(), "OK", "La justification est recevable.");
    }
    @Override
    public String obtenirNomRegle() { return "Contrôle de la justification"; }
}
