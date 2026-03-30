package com.darin.validateuracces.regle;

import com.darin.validateuracces.dto.DemandeAcces;
import com.darin.validateuracces.dto.ResultatRegle;
import org.springframework.stereotype.Component;

@Component
public class RegleRoleAdminStagiaire implements RegleValidationAcces {
    @Override
    public ResultatRegle evaluer(DemandeAcces demandeAcces) {
        String typeEmploye = demandeAcces.getTypeEmploye();
        String roleDemande = demandeAcces.getRoleDemande();
        if ("STAGIAIRE".equalsIgnoreCase(typeEmploye) && "ADMIN".equalsIgnoreCase(roleDemande)) {
            return new ResultatRegle(obtenirNomRegle(), "REJET", "Un stagiaire ne peut pas obtenir un accès administrateur.");
        }
        return new ResultatRegle(obtenirNomRegle(), "OK", "Le type d'employé est compatible avec le rôle demandé.");
    }
    @Override
    public String obtenirNomRegle() { return "Contrôle du rôle administrateur pour stagiaire"; }
}
