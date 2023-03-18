package com.example.arosaje.data;

import androidx.annotation.NonNull;

import com.example.arosaje.data.GardiennageEtat;

import java.io.Serializable;

public class User implements Serializable {
    private String nomUtilisateur;
    private Integer userId;
    // Aucun = 0, Demande = 1, En cours = 2
    private GardiennageEtat statusGardiennage;


    public User(String nomUtilisateur, Integer userId, GardiennageEtat statusGardiennage) {
        this.nomUtilisateur = nomUtilisateur;
        this.userId = userId;
        this.statusGardiennage = statusGardiennage;

    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public GardiennageEtat getstatusGardiennage() {
        return statusGardiennage;
    }

    public void setstatusGardiennage(GardiennageEtat statusGardiennage) {
        this.statusGardiennage = statusGardiennage;
    }
    @NonNull
    @Override
    public String toString(){
        return this.nomUtilisateur;
    }
}