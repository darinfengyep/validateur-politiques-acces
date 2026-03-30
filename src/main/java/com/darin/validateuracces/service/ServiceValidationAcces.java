package com.darin.validateuracces.service;

import com.darin.validateuracces.dto.DemandeAcces;
import com.darin.validateuracces.dto.ReponseValidation;
import com.darin.validateuracces.dto.ResultatRegle;
import com.darin.validateuracces.modele.DecisionAcces;
import com.darin.validateuracces.regle.RegleValidationAcces;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceValidationAcces {
    private final List<RegleValidationAcces> reglesValidationAcces;

    public ServiceValidationAcces(List<RegleValidationAcces> reglesValidationAcces) {
        this.reglesValidationAcces = reglesValidationAcces;
    }

    public ReponseValidation valider(DemandeAcces demandeAcces) {
        ReponseValidation reponseValidation = new ReponseValidation();
        List<ResultatRegle> resultats = reglesValidationAcces.stream().map(regle -> regle.evaluer(demandeAcces)).toList();
        reponseValidation.setResultatsRegles(resultats);
        boolean auMoinsUnRejet = resultats.stream().anyMatch(r -> "REJET".equalsIgnoreCase(r.getResultat()));
        boolean auMoinsUneAttente = resultats.stream().anyMatch(r -> "ATTENTE".equalsIgnoreCase(r.getResultat()));
        if (auMoinsUnRejet) {
            reponseValidation.setDecision(DecisionAcces.REJETEE);
            reponseValidation.setMessageGlobal("La demande d'accès est rejetée en raison d'au moins une règle bloquante.");
        } else if (auMoinsUneAttente) {
            reponseValidation.setDecision(DecisionAcces.EN_ATTENTE_VALIDATION);
            reponseValidation.setMessageGlobal("La demande d'accès nécessite une validation complémentaire.");
        } else {
            reponseValidation.setDecision(DecisionAcces.APPROUVEE);
            reponseValidation.setMessageGlobal("La demande d'accès est approuvée.");
        }
        return reponseValidation;
    }

    public List<String> listerRegles() {
        return reglesValidationAcces.stream().map(RegleValidationAcces::obtenirNomRegle).toList();
    }
}
