package com.example.serviceetudiant.model;

public class ServiceUser {
    private int idService;
    private int idUser;
    private String dateDemande;
    private String etatDemande;

    public ServiceUser(int idService, int idUser, String dateDemande, String etatDemande) {
        this.idService = idService;
        this.idUser = idUser;
        this.dateDemande = dateDemande;
        this.etatDemande = etatDemande;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(String dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(String etatDemande) {
        this.etatDemande = etatDemande;
    }
}
