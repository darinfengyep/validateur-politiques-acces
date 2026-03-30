package com.darin.validateuracces.dto;

import com.darin.validateuracces.modele.DecisionAcces;
import java.util.ArrayList;
import java.util.List;

public class ReponseValidation {
    private DecisionAcces decision;
    private String messageGlobal;
    private List<ResultatRegle> resultatsRegles = new ArrayList<>();

    public DecisionAcces getDecision() { return decision; }
    public void setDecision(DecisionAcces decision) { this.decision = decision; }
    public String getMessageGlobal() { return messageGlobal; }
    public void setMessageGlobal(String messageGlobal) { this.messageGlobal = messageGlobal; }
    public List<ResultatRegle> getResultatsRegles() { return resultatsRegles; }
    public void setResultatsRegles(List<ResultatRegle> resultatsRegles) { this.resultatsRegles = resultatsRegles; }
}
