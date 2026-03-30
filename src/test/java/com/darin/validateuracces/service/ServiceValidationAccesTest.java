package com.darin.validateuracces.service;

import com.darin.validateuracces.dto.DemandeAcces;
import com.darin.validateuracces.modele.DecisionAcces;
import com.darin.validateuracces.regle.RegleCoherenceDepartement;
import com.darin.validateuracces.regle.RegleJustification;
import com.darin.validateuracces.regle.RegleRoleAdminStagiaire;
import com.darin.validateuracces.regle.RegleSensibilite;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceValidationAccesTest {
    private final ServiceValidationAcces serviceValidationAcces = new ServiceValidationAcces(List.of(
        new RegleJustification(), new RegleRoleAdminStagiaire(), new RegleSensibilite(), new RegleCoherenceDepartement()
    ));

    @Test
    void doitRejeterUnStagiaireAvecRoleAdmin() {
        DemandeAcces demandeAcces = creerDemande();
        demandeAcces.setTypeEmploye("STAGIAIRE");
        demandeAcces.setRoleDemande("ADMIN");
        assertEquals(DecisionAcces.REJETEE, serviceValidationAcces.valider(demandeAcces).getDecision());
    }

    @Test
    void doitMettreEnAttenteUnAccesSensible() {
        DemandeAcces demandeAcces = creerDemande();
        demandeAcces.setNiveauSensibilite("ELEVEE");
        assertEquals(DecisionAcces.EN_ATTENTE_VALIDATION, serviceValidationAcces.valider(demandeAcces).getDecision());
    }

    @Test
    void doitApprouverUneDemandeSimple() {
        DemandeAcces demandeAcces = creerDemande();
        assertEquals(DecisionAcces.APPROUVEE, serviceValidationAcces.valider(demandeAcces).getDecision());
    }

    private DemandeAcces creerDemande() {
        DemandeAcces demandeAcces = new DemandeAcces();
        demandeAcces.setNomUtilisateur("Darin Fengyep");
        demandeAcces.setDepartement("FINANCE");
        demandeAcces.setTypeEmploye("EMPLOYE");
        demandeAcces.setRoleDemande("LECTURE");
        demandeAcces.setApplicationCible("SystemePaie");
        demandeAcces.setNiveauSensibilite("FAIBLE");
        demandeAcces.setJustification("Besoin d'accès pour traiter mes tâches quotidiennes.");
        return demandeAcces;
    }
}
