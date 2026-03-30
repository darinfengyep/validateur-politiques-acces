package com.darin.validateuracces.regle;

import com.darin.validateuracces.dto.DemandeAcces;
import com.darin.validateuracces.dto.ResultatRegle;
import org.springframework.stereotype.Component;

@Component
public class RegleCoherenceDepartement implements RegleValidationAcces {
    @Override
    public ResultatRegle evaluer(DemandeAcces demandeAcces) {
        String departement = demandeAcces.getDepartement();
        String application = demandeAcces.getApplicationCible();
        if ("FINANCE".equalsIgnoreCase(departement) && "SystemePaie".equalsIgnoreCase(application)) {
            return new ResultatRegle(obtenirNomRegle(), "OK", "Le département est cohérent avec l'application cible.");
        }
        if ("RH".equalsIgnoreCase(departement) && "PortailRH".equalsIgnoreCase(application)) {
            return new ResultatRegle(obtenirNomRegle(), "OK", "Le département est cohérent avec l'application cible.");
        }
        if ("TI".equalsIgnoreCase(departement)) {
            return new ResultatRegle(obtenirNomRegle(), "OK", "Le département TI peut accéder aux applications d'administration.");
        }
        return new ResultatRegle(obtenirNomRegle(), "ATTENTE", "La cohérence métier doit être validée manuellement.");
    }
    @Override
    public String obtenirNomRegle() { return "Contrôle de cohérence département / application"; }
}
