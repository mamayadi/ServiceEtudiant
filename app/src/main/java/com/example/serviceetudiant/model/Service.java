package com.example.serviceetudiant.model;

import java.io.Serializable;

public class Service implements Serializable {
    private int id;
    private String libelle;

    public Service(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
