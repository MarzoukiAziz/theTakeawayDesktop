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
public class Restaurant {
    private int id,telephone;
    private String nom , adresse , description , architecture ;
    private Date heure_ouverture,heure_fermeture;
    private array images;

    private static class array {

        public array() {
        }
    }

    public Restaurant(int id, int telephone, String nom, String adresse, String description, String architecture, Date heure_ouverture, Date heure_fermeture, array images) {
        this.id = id;
        this.telephone = telephone;
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        this.architecture = architecture;
        this.heure_ouverture = heure_ouverture;
        this.heure_fermeture = heure_fermeture;
        this.images = images;
    }

    public Restaurant(int telephone, String nom, String adresse, String description, Date heure_ouverture, Date heure_fermeture) {
        this.telephone = telephone;
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        this.heure_ouverture = heure_ouverture;
        this.heure_fermeture = heure_fermeture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
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

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public Date getHeure_ouverture() {
        return heure_ouverture;
    }

    public void setHeure_ouverture(Date heure_ouverture) {
        this.heure_ouverture = heure_ouverture;
    }

    public Date getHeure_fermeture() {
        return heure_fermeture;
    }

    public void setHeure_fermeture(Date heure_fermeture) {
        this.heure_fermeture = heure_fermeture;
    }

    public array getImages() {
        return images;
    }

    public void setImages(array images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + id + ", telephone=" + telephone + ", nom=" + nom + ", adresse=" + adresse + ", description=" + description + ", architecture=" + architecture + ", heure_ouverture=" + heure_ouverture + ", heure_fermeture=" + heure_fermeture + ", images=" + images + '}';
    }
    
    
    
    
}
