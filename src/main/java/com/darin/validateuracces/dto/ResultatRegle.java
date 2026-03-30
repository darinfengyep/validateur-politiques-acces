package com.darin.validateuracces.dto;

public class ResultatRegle {
    private String nomRegle;
    private String resultat;
    private String message;

    public ResultatRegle() {}
    public ResultatRegle(String nomRegle, String resultat, String message) {
        this.nomRegle = nomRegle;
        this.resultat = resultat;
        this.message = message;
    }
    public String getNomRegle() { return nomRegle; }
    public void setNomRegle(String nomRegle) { this.nomRegle = nomRegle; }
    public String getResultat() { return resultat; }
    public void setResultat(String resultat) { this.resultat = resultat; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
