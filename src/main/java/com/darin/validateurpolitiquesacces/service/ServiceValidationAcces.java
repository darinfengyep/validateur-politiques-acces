package com.darin.validateurpolitiquesacces.service;

import com.darin.validateurpolitiquesacces.dto.ReponseTableauBord;
import com.darin.validateurpolitiquesacces.dto.ReponseValidationAcces;
import com.darin.validateurpolitiquesacces.dto.RequeteDecisionManuelle;
import com.darin.validateurpolitiquesacces.dto.RequeteValidationAcces;
import com.darin.validateurpolitiquesacces.entite.DemandeAcces;
import com.darin.validateurpolitiquesacces.entite.UtilisateurHabilite;
import com.darin.validateurpolitiquesacces.enumeration.DecisionAcces;
import com.darin.validateurpolitiquesacces.enumeration.StatutDemande;
import com.darin.validateurpolitiquesacces.repository.DemandeAccesRepository;
import com.darin.validateurpolitiquesacces.repository.UtilisateurHabiliteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceValidationAcces {

    private final DemandeAccesRepository demandeAccesRepository;
    private final UtilisateurHabiliteRepository utilisateurHabiliteRepository;

    public ServiceValidationAcces(DemandeAccesRepository demandeAccesRepository,
                                  UtilisateurHabiliteRepository utilisateurHabiliteRepository) {
        this.demandeAccesRepository = demandeAccesRepository;
        this.utilisateurHabiliteRepository = utilisateurHabiliteRepository;
    }

    public ReponseValidationAcces validerDemande(RequeteValidationAcces requete) {
        List<String> regles = new ArrayList<>();
        DecisionAcces decision = DecisionAcces.ACCEPTE;
        String message = "Demande approuvée automatiquement.";

        if (requete.getJustification().trim().length() < 10) {
            regles.add("Justification insuffisante");
            decision = DecisionAcces.REFUSE;
            message = "La justification fournie est insuffisante.";
        }

        if ("STAGIAIRE".equalsIgnoreCase(requete.getTypeEmploye())
                && "ADMIN".equalsIgnoreCase(requete.getRoleDemande())) {
            regles.add("Un stagiaire ne peut pas obtenir un rôle ADMIN");
            decision = DecisionAcces.REFUSE;
            message = "Le rôle demandé n'est pas autorisé pour ce type d'employé.";
        }

        if ("HIGH".equalsIgnoreCase(requete.getNiveauSensibilite())
                || "ELEVEE".equalsIgnoreCase(requete.getNiveauSensibilite())) {
            regles.add("Accès sensible nécessitant une validation complémentaire");
            if (decision != DecisionAcces.REFUSE) {
                decision = DecisionAcces.A_VALIDER;
                message = "La demande a été placée en attente de validation manuelle.";
            }
        }

        if ("Finance".equalsIgnoreCase(requete.getDepartement())
                && "RH".equalsIgnoreCase(requete.getApplication())) {
            regles.add("Incompatibilité entre le département et l'application demandée");
            decision = DecisionAcces.REFUSE;
            message = "Le département n'est pas autorisé à accéder à cette application.";
        }

        if (regles.isEmpty()) {
            regles.add("Aucune règle bloquante détectée");
        }

        DemandeAcces demande = new DemandeAcces();
        demande.setNomUtilisateur(requete.getNomUtilisateur());
        demande.setDepartement(requete.getDepartement());
        demande.setTypeEmploye(requete.getTypeEmploye());
        demande.setRoleDemande(requete.getRoleDemande());
        demande.setApplication(requete.getApplication());
        demande.setNiveauSensibilite(requete.getNiveauSensibilite());
        demande.setJustification(requete.getJustification());
        demande.setDecision(decision);
        demande.setStatutDemande(convertirDecisionEnStatut(decision));
        demande.setReglesDeclenchees(String.join(" | ", regles));
        demande.setDateValidation(LocalDateTime.now());
        if (decision == DecisionAcces.REFUSE) {
            demande.setCommentaireDecision("Décision automatique : refus selon les règles métier.");
            demande.setValidePar("Moteur automatique");
            demande.setDateDecisionManuelle(LocalDateTime.now());
        } else if (decision == DecisionAcces.ACCEPTE) {
            demande.setCommentaireDecision("Décision automatique : accès approuvé.");
            demande.setValidePar("Moteur automatique");
            demande.setDateDecisionManuelle(LocalDateTime.now());
        } else {
            demande.setCommentaireDecision("En attente d'une décision manuelle.");
        }
        demande = demandeAccesRepository.save(demande);

        return construireReponse(demande, regles, message);
    }

    public DemandeAcces deciderManuellement(Long id, RequeteDecisionManuelle requete, String validePar) {
        DemandeAcces demande = demandeAccesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Demande introuvable : " + id));

        demande.setStatutDemande(requete.getStatutCible());
        demande.setCommentaireDecision(requete.getCommentaireDecision());
        demande.setValidePar(validePar);
        demande.setDateDecisionManuelle(LocalDateTime.now());

        if (requete.getStatutCible() == StatutDemande.APPROUVEE) {
            demande.setDecision(DecisionAcces.ACCEPTE);
        } else if (requete.getStatutCible() == StatutDemande.REFUSEE) {
            demande.setDecision(DecisionAcces.REFUSE);
        } else if (requete.getStatutCible() == StatutDemande.A_REVOIR || requete.getStatutCible() == StatutDemande.EN_ATTENTE) {
            demande.setDecision(DecisionAcces.A_VALIDER);
        }

        return demandeAccesRepository.save(demande);
    }

    public List<DemandeAcces> recupererHistorique() {
        return demandeAccesRepository.findAll()
                .stream()
                .sorted((a, b) -> b.getDateValidation().compareTo(a.getDateValidation()))
                .collect(Collectors.toList());
    }

    public List<UtilisateurHabilite> recupererTousLesUtilisateurs() {
        return utilisateurHabiliteRepository.findAll()
                .stream()
                .sorted((a, b) -> a.getNomUtilisateur().compareToIgnoreCase(b.getNomUtilisateur()))
                .collect(Collectors.toList());
    }

    public List<String> recupererRegles() {
        return Arrays.asList(
                "Un stagiaire ne peut pas demander un rôle ADMIN",
                "Une demande sensible passe en statut EN_ATTENTE",
                "Une application incompatible avec le département est refusée",
                "Une justification doit être suffisamment détaillée",
                "Un administrateur peut approuver, refuser ou demander une revue complémentaire"
        );
    }

    public ReponseTableauBord recupererTableauBord() {
        ReponseTableauBord reponse = new ReponseTableauBord();
        reponse.setTotalDemandes(demandeAccesRepository.count());
        reponse.setDemandesEnAttente(demandeAccesRepository.countByStatutDemande(StatutDemande.EN_ATTENTE));
        reponse.setDemandesApprouvees(demandeAccesRepository.countByStatutDemande(StatutDemande.APPROUVEE));
        reponse.setDemandesRefusees(demandeAccesRepository.countByStatutDemande(StatutDemande.REFUSEE));
        reponse.setDemandesARevoir(demandeAccesRepository.countByStatutDemande(StatutDemande.A_REVOIR));
        return reponse;
    }

    private ReponseValidationAcces construireReponse(DemandeAcces demande, List<String> regles, String message) {
        ReponseValidationAcces reponse = new ReponseValidationAcces();
        reponse.setNomUtilisateur(demande.getNomUtilisateur());
        reponse.setDepartement(demande.getDepartement());
        reponse.setTypeEmploye(demande.getTypeEmploye());
        reponse.setApplication(demande.getApplication());
        reponse.setDecision(demande.getDecision());
        reponse.setReglesDeclenchees(regles);
        reponse.setMessage(message);
        reponse.setDateValidation(demande.getDateValidation());
        return reponse;
    }

    private StatutDemande convertirDecisionEnStatut(DecisionAcces decision) {
        if (decision == DecisionAcces.ACCEPTE) {
            return StatutDemande.APPROUVEE;
        }
        if (decision == DecisionAcces.REFUSE) {
            return StatutDemande.REFUSEE;
        }
        return StatutDemande.EN_ATTENTE;
    }
}
