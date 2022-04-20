/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.sql.Date;

/**
 *
 * @author Bouga
 */
public class promotion {
    
    private int id , element_id ; 
    private Date date_debut , date_fin , heure_debut,heure_fin ;
    private String banner ;
    private int prix_promo;

    public promotion(int id, int element_id, Date date_debut, Date date_fin, Date heure_debut, Date heure_fin, String banner, int prix_promo) {
        this.id = id;
        this.element_id = element_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.banner = banner;
        this.prix_promo = prix_promo;
    }

    public promotion(int id, int element_id) {
        this.id = id;
        this.element_id = element_id;
    }

    public promotion(int element_id, Date date_debut, Date date_fin, Date heure_debut, Date heure_fin, String banner, int prix_promo) {
        this.element_id = element_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.banner = banner;
        this.prix_promo = prix_promo;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getElement_id() {
        return element_id;
    }

    public void setElement_id(int element_id) {
        this.element_id = element_id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Date getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(Date heure_debut) {
        this.heure_debut = heure_debut;
    }

    public Date getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(Date heure_fin) {
        this.heure_fin = heure_fin;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getPrix_promo() {
        return prix_promo;
    }

    public void setPrix_promo(int prix_promo) {
        this.prix_promo = prix_promo;
    }

    @Override
    public String toString() {
        return "promotion{" + "id=" + id + ", element_id=" + element_id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", heure_debut=" + heure_debut + ", heure_fin=" + heure_fin + ", banner=" + banner + ", prix_promo=" + prix_promo + '}';
    }
    
    
    
}
