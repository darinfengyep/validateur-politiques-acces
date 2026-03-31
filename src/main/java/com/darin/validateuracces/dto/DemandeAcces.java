package com.darin.validateuracces.dto;

import jakarta.validation.constraints.NotBlank;

public class DemandeAcces {
    @NotBlank(message = "Le nom de l'utilisateur est obligatoire")
    private String nomUtilisateur;
    @NotBlank(message = "Le département est obligatoire")
    private String departement;
    @NotBlank(message = "Le type d'employé est obligatoire")
    private String typeEmploye;
    @NotBlank(message = "Le rôle demandé est obligatoire")
    private String roleDemande;
    @NotBlank(message = "L'application cible est obligatoire")
    private String applicationCible;
    @NotBlank(message = "Le niveau de sensibilité est obligatoire")
    private String niveauSensibilite;
    @NotBlank(message = "La justification est obligatoire")
    private String justification;

    public String getNomUtilisateur() { return nomUtilisateur; }
    public void setNomUtilisateur(String nomUtilisateur) { this.nomUtilisateur = nomUtilisateur; }
    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }
    public String getTypeEmploye() { return typeEmploye; }
    public void setTypeEmploye(String typeEmploye) { this.typeEmploye = typeEmploye; }
    public String getRoleDemande() { return roleDemande; }
    public void setRoleDemande(String roleDemande) { this.roleDemande = roleDemande; }
    public String getApplicationCible() { return applicationCible; }
    public void setApplicationCible(String applicationCible) { this.applicationCible = applicationCible; }
    public String getNiveauSensibilite() { return niveauSensibilite; }
    public void setNiveauSensibilite(String niveauSensibilite) { this.niveauSensibilite = niveauSensibilite; }
    public String getJustification() { return justification; }
    public void setJustification(String justification) { this.justification = justification; }
}
