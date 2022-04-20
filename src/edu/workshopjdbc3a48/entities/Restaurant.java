/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.util.ArrayList;

/**
 *
 * @author moham
 */
public class Restaurant {
      private int id;
    private String nom;
    private String adresse;
    private String description;
    private String telephone;
    private String heure_ouverture;
    private String heure_fermeture;
    private ArrayList<String> images;
    private float x;
    private float y;

    public Restaurant(int id, String nom, String adresse, String description, String telephone, String heure_ouverture, String heure_fermeture, ArrayList<String> images, float x, float y) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        this.telephone = telephone;
        this.heure_ouverture = heure_ouverture;
        this.heure_fermeture = heure_fermeture;
        this.images = images;
        this.x = x;
        this.y = y;
    }

    public Restaurant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getHeure_ouverture() {
        return heure_ouverture;
    }

    public void setHeure_ouverture(String heure_ouverture) {
        this.heure_ouverture = heure_ouverture;
    }

    public String getHeure_fermeture() {
        return heure_fermeture;
    }

    public void setHeure_fermeture(String heure_fermeture) {
        this.heure_fermeture = heure_fermeture;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", description=" + description + ", telephone=" + telephone + ", heure_ouverture=" + heure_ouverture + ", heure_fermeture=" + heure_fermeture + ", images=" + images + ", x=" + x + ", y=" + y + '}';
    }
    
}
