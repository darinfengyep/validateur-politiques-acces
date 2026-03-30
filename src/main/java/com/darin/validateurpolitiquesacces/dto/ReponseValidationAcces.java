package com.darin.validateurpolitiquesacces.dto;

import com.darin.validateurpolitiquesacces.enumeration.DecisionAcces;

import java.time.LocalDateTime;
import java.util.List;

public class ReponseValidationAcces {

    private String nomUtilisateur;
    private String departement;
    private String typeEmploye;
    private String application;
    private DecisionAcces decision;
    private List<String> reglesDeclenchees;
    private String message;
    private LocalDateTime dateValidation;

    public String getNomUtilisateur() { return nomUtilisateur; }
    public void setNomUtilisateur(String nomUtilisateur) { this.nomUtilisateur = nomUtilisateur; }
    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }
    public String getTypeEmploye() { return typeEmploye; }
    public void setTypeEmploye(String typeEmploye) { this.typeEmploye = typeEmploye; }
    public String getApplication() { return application; }
    public void setApplication(String application) { this.application = application; }
    public DecisionAcces getDecision() { return decision; }
    public void setDecision(DecisionAcces decision) { this.decision = decision; }
    public List<String> getReglesDeclenchees() { return reglesDeclenchees; }
    public void setReglesDeclenchees(List<String> reglesDeclenchees) { this.reglesDeclenchees = reglesDeclenchees; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDateTime getDateValidation() { return dateValidation; }
    public void setDateValidation(LocalDateTime dateValidation) { this.dateValidation = dateValidation; }
}
