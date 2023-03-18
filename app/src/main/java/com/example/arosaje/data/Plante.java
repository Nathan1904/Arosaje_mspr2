package com.example.arosaje.data;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Plante implements Serializable {
    private String nomPlante;
    private Integer planteId;
    
    private Integer refPlanteId;
    private String photo;
    private String date;
    private String localisation;
    private String commentaire;

    public Plante(String nomPlante, Integer planteId, Integer refPlanteId, String photo, String date, String localisation, String commentaire) {
        this.nomPlante = nomPlante;
        this.planteId = planteId;
        this.refPlanteId = refPlanteId;
        this.photo = photo;
        this.date = date;
        this.localisation = localisation;
        this.commentaire = commentaire;
    }

    public String getNomPlante() {
        return nomPlante;
    }

    public void setNomPlante(String nomPlante) {
        this.nomPlante = nomPlante;
    }

    public Integer getPlanteId() {
        return planteId;
    }

    public void setPlanteId(Integer planteId) {
        this.planteId = planteId;
    }

    public Integer getrefPlanteId() {
        return refPlanteId;
    }

    public void setrefPlanteId(Integer refPlanteId) {
        this.refPlanteId = refPlanteId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @NonNull
    @Override
    public String toString(){
        return this.nomPlante;
    }
}