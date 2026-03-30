package com.darin.validateurpolitiquesacces.config;

import com.darin.validateurpolitiquesacces.entite.UtilisateurHabilite;
import com.darin.validateurpolitiquesacces.repository.UtilisateurHabiliteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChargeurDonneesInitiales {

    @Bean
    CommandLineRunner initialiserUtilisateurs(UtilisateurHabiliteRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(utilisateur("Aurore Dieptong", "RH", "EMPLOYE", "RH Portal, Paie, GED", "GESTIONNAIRE", "ACTIF"));
                repository.save(utilisateur("Darin Fengyep", "Informatique", "EMPLOYE", "ServiceNow, Portail Acces, CMDB", "ANALYSTE", "ACTIF"));
                repository.save(utilisateur("Junior Mvondo", "Finance", "STAGIAIRE", "Reporting Finance", "LECTURE", "ACTIF"));
                repository.save(utilisateur("Mireille Tchana", "Juridique", "CONSULTANT", "GED, Contrats, Portail Acces", "VALIDATEUR", "ACTIF"));
                repository.save(utilisateur("Pauline Nanga", "Operations", "EMPLOYE", "Supervision, CMDB, Inventaire", "OPERATEUR", "SUSPENDU"));
            }
        };
    }

    private UtilisateurHabilite utilisateur(String nom, String departement, String typeEmploye,
                                            String applications, String role, String statut) {
        UtilisateurHabilite utilisateur = new UtilisateurHabilite();
        utilisateur.setNomUtilisateur(nom);
        utilisateur.setDepartement(departement);
        utilisateur.setTypeEmploye(typeEmploye);
        utilisateur.setApplicationsHabilitees(applications);
        utilisateur.setRolePrincipal(role);
        utilisateur.setStatut(statut);
        return utilisateur;
    }
}
