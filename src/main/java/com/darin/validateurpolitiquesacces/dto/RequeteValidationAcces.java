package com.darin.validateurpolitiquesacces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RequeteValidationAcces {

    @NotBlank
    private String nomUtilisateur;
    @NotBlank
    private String departement;
    @NotBlank
    private String typeEmploye;
    @NotBlank
    private String roleDemande;
    @NotBlank
    private String application;
    @NotBlank
    private String niveauSensibilite;
    @NotBlank
    @Size(min = 10, message = "La justification doit contenir au moins 10 caractères")
    private String justification;

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
}
