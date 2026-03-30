package com.darin.validateurpolitiquesacces.service;

import com.darin.validateurpolitiquesacces.dto.ReponseValidationAcces;
import com.darin.validateurpolitiquesacces.dto.RequeteValidationAcces;
import com.darin.validateurpolitiquesacces.enumeration.DecisionAcces;
import com.darin.validateurpolitiquesacces.entite.DemandeAcces;
import com.darin.validateurpolitiquesacces.repository.DemandeAccesRepository;
import com.darin.validateurpolitiquesacces.repository.UtilisateurHabiliteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ServiceValidationAccesTest {

    private ServiceValidationAcces serviceValidationAcces;
    private DemandeAccesRepository demandeAccesRepository;

    @BeforeEach
    void initialiser() {
        demandeAccesRepository = Mockito.mock(DemandeAccesRepository.class);
        serviceValidationAcces = new ServiceValidationAcces(
                demandeAccesRepository,
                Mockito.mock(UtilisateurHabiliteRepository.class)
        );
    }

    @Test
    void doitRefuserUnAdminPourUnStagiaire() {
        RequeteValidationAcces requete = new RequeteValidationAcces();
        requete.setNomUtilisateur("Junior");
        requete.setDepartement("Finance");
        requete.setTypeEmploye("STAGIAIRE");
        requete.setRoleDemande("ADMIN");
        requete.setApplication("Reporting Finance");
        requete.setNiveauSensibilite("LOW");
        requete.setJustification("Besoin d'accès complet pour un exercice de test.");

        when(demandeAccesRepository.save(any(DemandeAcces.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ReponseValidationAcces reponse = serviceValidationAcces.validerDemande(requete);

        assertNotNull(reponse);
        assertEquals(DecisionAcces.REFUSE, reponse.getDecision());
        assertEquals("Junior", reponse.getNomUtilisateur());
    }
}
