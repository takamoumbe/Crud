package com.example.crud.BD;

public class EleveModel {
    Integer id;
    String nom;
    String prenom;
    String delete_at;
    String update_at;
    String create_at;
    Integer etat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(String delete_at) {
        this.delete_at = delete_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "EleveModel{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", delete_at='" + delete_at + '\'' +
                ", update_at='" + update_at + '\'' +
                ", create_at='" + create_at + '\'' +
                ", etat=" + etat +
                '}';
    }

    public EleveModel(Integer id, String nom, String prenom, String delete_at, String update_at, String create_at, Integer etat) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.delete_at = delete_at;
        this.update_at = update_at;
        this.create_at = create_at;
        this.etat = etat;
    }
}
