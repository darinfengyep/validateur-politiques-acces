package com.darin.validateurpolitiquesacces.dto;

public class ReponseTableauBord {

    private long totalDemandes;
    private long demandesEnAttente;
    private long demandesApprouvees;
    private long demandesRefusees;
    private long demandesARevoir;

    public long getTotalDemandes() {
        return totalDemandes;
    }

    public void setTotalDemandes(long totalDemandes) {
        this.totalDemandes = totalDemandes;
    }

    public long getDemandesEnAttente() {
        return demandesEnAttente;
    }

    public void setDemandesEnAttente(long demandesEnAttente) {
        this.demandesEnAttente = demandesEnAttente;
    }

    public long getDemandesApprouvees() {
        return demandesApprouvees;
    }

    public void setDemandesApprouvees(long demandesApprouvees) {
        this.demandesApprouvees = demandesApprouvees;
    }

    public long getDemandesRefusees() {
        return demandesRefusees;
    }

    public void setDemandesRefusees(long demandesRefusees) {
        this.demandesRefusees = demandesRefusees;
    }

    public long getDemandesARevoir() {
        return demandesARevoir;
    }

    public void setDemandesARevoir(long demandesARevoir) {
        this.demandesARevoir = demandesARevoir;
    }
}
