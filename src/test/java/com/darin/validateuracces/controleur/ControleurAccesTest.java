package com.darin.validateuracces.controleur;

import com.darin.validateuracces.service.ServiceValidationAcces;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ControleurAcces.class)
@AutoConfigureMockMvc
class ControleurAccesTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private ServiceValidationAcces serviceValidationAcces;

    @Test
    void doitRetournerLaSanteDeLApi() throws Exception {
        mockMvc.perform(get("/api/sante")).andExpect(status().isOk()).andExpect(jsonPath("$.statut").value("OK"));
    }

    @Test
    void doitRetournerLesRegles() throws Exception {
        given(serviceValidationAcces.listerRegles()).willReturn(List.of("Règle 1", "Règle 2"));
        mockMvc.perform(get("/api/acces/regles")).andExpect(status().isOk()).andExpect(jsonPath("$[0]").value("Règle 1"));
    }
}
