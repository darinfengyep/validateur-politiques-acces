package com.darin.validateurpolitiquesacces.dto;

import com.darin.validateurpolitiquesacces.enumeration.StatutDemande;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RequeteDecisionManuelle {

    @NotNull(message = "Le statut cible est obligatoire")
    private StatutDemande statutCible;

    @NotBlank(message = "Le commentaire de décision est obligatoire")
    @Size(min = 5, max = 500, message = "Le commentaire doit contenir entre 5 et 500 caractères")
    private String commentaireDecision;

    public StatutDemande getStatutCible() {
        return statutCible;
    }

    public void setStatutCible(StatutDemande statutCible) {
        this.statutCible = statutCible;
    }

    public String getCommentaireDecision() {
        return commentaireDecision;
    }

    public void setCommentaireDecision(String commentaireDecision) {
        this.commentaireDecision = commentaireDecision;
    }
}
