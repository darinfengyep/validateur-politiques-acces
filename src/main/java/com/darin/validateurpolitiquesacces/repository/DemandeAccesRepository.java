package com.darin.validateurpolitiquesacces.repository;

import com.darin.validateurpolitiquesacces.entite.DemandeAcces;
import com.darin.validateurpolitiquesacces.enumeration.StatutDemande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeAccesRepository extends JpaRepository<DemandeAcces, Long> {
    long countByStatutDemande(StatutDemande statutDemande);
}
