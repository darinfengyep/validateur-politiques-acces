package com.darin.validateurpolitiquesacces.entite;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateurs_habilites")
public class UtilisateurHabilite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomUtilisateur;
    private String departement;
    private String typeEmploye;
    private String applicationsHabilitees;
    private String rolePrincipal;
    private String statut;

    public Long getId() { return id; }
    public String getNomUtilisateur() { return nomUtilisateur; }
    public void setNomUtilisateur(String nomUtilisateur) { this.nomUtilisateur = nomUtilisateur; }
    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }
    public String getTypeEmploye() { return typeEmploye; }
    public void setTypeEmploye(String typeEmploye) { this.typeEmploye = typeEmploye; }
    public String getApplicationsHabilitees() { return applicationsHabilitees; }
    public void setApplicationsHabilitees(String applicationsHabilitees) { this.applicationsHabilitees = applicationsHabilitees; }
    public String getRolePrincipal() { return rolePrincipal; }
    public void setRolePrincipal(String rolePrincipal) { this.rolePrincipal = rolePrincipal; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}
