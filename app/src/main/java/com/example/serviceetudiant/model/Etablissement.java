package com.example.serviceetudiant.model;

import java.io.Serializable;

public class Etablissement implements Serializable {
    private int id;
    private String libelle;
    private String adresse;

    public Etablissement(int id, String libelle, String adresse) {
        this.id = id;
        this.libelle = libelle;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
