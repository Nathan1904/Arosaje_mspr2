package com.example.arosaje.data;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Gardiennage implements Serializable {
    private Integer gardiennageId;
    private User gardien;
    private User proprietaire;


    public Gardiennage(Integer gardiennageId, User gardien, User proprietaire) {
        this.gardiennageId = gardiennageId;
        this.gardien = gardien;
        this.proprietaire = proprietaire;

    }

    public Integer getgardiennageId() {
        return gardiennageId;
    }
    public void setgardiennageId(Integer gardiennageId) {
        this.gardiennageId = gardiennageId;
    }

    public User getgardien() {
        return gardien;
    }
    public void setgardien(User gardien) {
        this.gardien = gardien;
    }

    public User getproprietaire() {
        return proprietaire;
    }
    public void setproprietaire(User proprietaire) {
        this.proprietaire = proprietaire;
    }

    @NonNull
    @Override
    public String toString(){
        return "gardiennage de "+this.proprietaire.getNomUtilisateur();
    }

}