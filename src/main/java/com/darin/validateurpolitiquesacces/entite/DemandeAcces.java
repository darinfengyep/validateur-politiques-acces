package com.darin.validateurpolitiquesacces.entite;

import com.darin.validateurpolitiquesacces.enumeration.DecisionAcces;
import com.darin.validateurpolitiquesacces.enumeration.StatutDemande;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "demandes_acces")
public class DemandeAcces {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomUtilisateur;
    private String departement;
    private String typeEmploye;
    private String roleDemande;
    private String application;
    private String niveauSensibilite;

    @Column(length = 1000)
    private String justification;

    @Enumerated(EnumType.STRING)
    private DecisionAcces decision;

    @Enumerated(EnumType.STRING)
    private StatutDemande statutDemande;

    @Column(length = 1500)
    private String reglesDeclenchees;

    private LocalDateTime dateValidation;

    @Column(length = 500)
    private String commentaireDecision;

    private String validePar;

    private LocalDateTime dateDecisionManuelle;

    public Long getId() { return id; }
    public String getNomUtilisateur() { return nomUtilisateur; }
    public void setNomUtilisateur(String nomUtilisateur) { this.nomUtilisateur = nomUtilisateur; }
    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }
    public String getTypeEmploye() { return typeEmploye; }
    public void setTypeEmploye(String typeEmploye) { this.typeEmploye = typeEmploye; }
    public String getRoleDemande() { return roleDemande; }
    public void setRoleDemande(String roleDemande) { this.roleDemande = roleDemande; }
    public String getApplication() { return application; }
    public void setApplication(String application) { this.application = application; }
    public String getNiveauSensibilite() { return niveauSensibilite; }
    public void setNiveauSensibilite(String niveauSensibilite) { this.niveauSensibilite = niveauSensibilite; }
    public String getJustification() { return justification; }
    public void setJustification(String justification) { this.justification = justification; }
    public DecisionAcces getDecision() { return decision; }
    public void setDecision(DecisionAcces decision) { this.decision = decision; }
    public StatutDemande getStatutDemande() { return statutDemande; }
    public void setStatutDemande(StatutDemande statutDemande) { this.statutDemande = statutDemande; }
    public String getReglesDeclenchees() { return reglesDeclenchees; }
    public void setReglesDeclenchees(String reglesDeclenchees) { this.reglesDeclenchees = reglesDeclenchees; }
    public LocalDateTime getDateValidation() { return dateValidation; }
    public void setDateValidation(LocalDateTime dateValidation) { this.dateValidation = dateValidation; }
    public String getCommentaireDecision() { return commentaireDecision; }
    public void setCommentaireDecision(String commentaireDecision) { this.commentaireDecision = commentaireDecision; }
    public String getValidePar() { return validePar; }
    public void setValidePar(String validePar) { this.validePar = validePar; }
    public LocalDateTime getDateDecisionManuelle() { return dateDecisionManuelle; }
    public void setDateDecisionManuelle(LocalDateTime dateDecisionManuelle) { this.dateDecisionManuelle = dateDecisionManuelle; }
}
