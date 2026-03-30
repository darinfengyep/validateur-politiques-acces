
package com.darin.validateurpolitiquesacces.controleur;

import com.darin.validateurpolitiquesacces.dto.RequeteValidationAcces;
import com.darin.validateurpolitiquesacces.entite.DemandeAcces;
import com.darin.validateurpolitiquesacces.entite.UtilisateurHabilite;
import com.darin.validateurpolitiquesacces.enumeration.DecisionAcces;
import com.darin.validateurpolitiquesacces.service.ServiceValidationAcces;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {ControleurAcces.class, ControleurSante.class, ControleurSession.class, ControleurConnexion.class})
@AutoConfigureMockMvc
@Import(com.darin.validateurpolitiquesacces.config.ConfigurationSecurite.class)
class ControleurAccesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ServiceValidationAcces serviceValidationAcces;

    @Test
    void doitRetournerSanteSansAuthentification() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statut").value("OK"));
    }

    @Test
    @WithMockUser(roles = {"ANALYSTE"})
    void doitValiderUneDemande() throws Exception {
        RequeteValidationAcces requete = new RequeteValidationAcces();
        requete.setNomUtilisateur("Darin Fengyep");
        requete.setDepartement("Informatique");
        requete.setTypeEmploye("EMPLOYE");
        requete.setRoleDemande("ANALYSTE");
        requete.setApplication("ServiceNow");
        requete.setNiveauSensibilite("LOW");
        requete.setJustification("Accès nécessaire pour le traitement des demandes.");

        var reponse = new com.darin.validateurpolitiquesacces.dto.ReponseValidationAcces();
        reponse.setNomUtilisateur("Darin Fengyep");
        reponse.setApplication("ServiceNow");
        reponse.setDecision(DecisionAcces.ACCEPTE);
        reponse.setMessage("Demande approuvée.");
        reponse.setReglesDeclenchees(List.of("Aucune règle bloquante détectée"));
        given(serviceValidationAcces.validerDemande(any())).willReturn(reponse);

        mockMvc.perform(post("/api/acces/valider")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requete)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.decision").value("ACCEPTE"));
    }

    @Test
    @WithMockUser(roles = {"ANALYSTE"})
    void doitRetournerHistorique() throws Exception {
        DemandeAcces element = new DemandeAcces();
        element.setNomUtilisateur("Darin Fengyep");
        element.setDepartement("Informatique");
        element.setTypeEmploye("EMPLOYE");
        element.setApplication("ServiceNow");
        element.setDecision(DecisionAcces.ACCEPTE);
        element.setDateValidation(LocalDateTime.now());
        element.setReglesDeclenchees("Aucune règle bloquante détectée");
        given(serviceValidationAcces.recupererHistorique()).willReturn(List.of(element));

        mockMvc.perform(get("/api/acces/historique"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nomUtilisateur").value("Darin Fengyep"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void doitRetournerUtilisateursPourAdmin() throws Exception {
        UtilisateurHabilite utilisateur = new UtilisateurHabilite();
        utilisateur.setNomUtilisateur("Aurore Dieptong");
        utilisateur.setDepartement("RH");
        utilisateur.setTypeEmploye("EMPLOYE");
        utilisateur.setApplicationsHabilitees("RH Portal, Paie");
        given(serviceValidationAcces.recupererTousLesUtilisateurs()).willReturn(List.of(utilisateur));

        mockMvc.perform(get("/api/acces/utilisateurs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departement").value("RH"));
    }
}
