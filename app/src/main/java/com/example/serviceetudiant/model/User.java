package com.example.serviceetudiant.model;

public class User {
    private int id;
    private int iu;
    private String nom;
    private String prenom;
    private int id_etablissement;
    private String login;
    private String passwd;
    private String autorisation;

    public User(int id, int iu, String nom, String prenom, int id_etablissement, String login, String passwd, String autorisation) {
        this.id = id;
        this.iu = iu;
        this.nom = nom;
        this.prenom = prenom;
        this.id_etablissement = id_etablissement;
        this.login = login;
        this.passwd = passwd;
        this.autorisation = autorisation;
    }

    public int getId() {
        return id;
    }

    public int getIu() {
        return iu;
    }

    public void setIu(int iu) {
        this.iu = iu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getId_etablissement() {
        return id_etablissement;
    }

    public void setId_etablissement(int id_etablissement) {
        this.id_etablissement = id_etablissement;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(String autorisation) {
        this.autorisation = autorisation;
    }
}
