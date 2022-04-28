package edu.thetakeaway.entities;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Objects;

public class Restaurant {

    private int id;
    private String nom;
    private String adresse;
    private String description;
    private String telephone;
    private Time heure_ouverture;
    private Time heure_fermeture;
    private String image;
    private double x;
    private double y;

    public Restaurant(int id, String nom, String adresse, String description, String telephone, Time heure_ouverture, Time heure_fermeture, String image, double x, double y) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        this.telephone = telephone;
        this.heure_ouverture = heure_ouverture;
        this.heure_fermeture = heure_fermeture;
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public Restaurant(String nom, String adresse, String description, String telephone, Time heure_ouverture, Time heure_fermeture, String image, double x, double y) {
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        this.telephone = telephone;
        this.heure_ouverture = heure_ouverture;
        this.heure_fermeture = heure_fermeture;
        this.image = image;
        this.x = x;
        this.y = y;
    }


    public Restaurant(int id, String nom) {
        this.id = id;
        this.nom = nom;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Time getHeure_ouverture() {
        return heure_ouverture;
    }

    public void setHeure_ouverture(Time heure_ouverture) {
        this.heure_ouverture = heure_ouverture;
    }

    public Time getHeure_fermeture() {
        return heure_fermeture;
    }

    public void setHeure_fermeture(Time heure_fermeture) {
        this.heure_fermeture = heure_fermeture;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Restaurant other = (Restaurant) obj;
        if (this.id != other.id) {
            return false;
        }
    
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.telephone, other.telephone)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.heure_ouverture, other.heure_ouverture)) {
            return false;
        }
        if (!Objects.equals(this.heure_fermeture, other.heure_fermeture)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", description=" + description + ", telephone=" + telephone + ", heure_ouverture=" + heure_ouverture + ", heure_fermeture=" + heure_fermeture + ", image=" + image + ", x=" + x + ", y=" + y + '}';
    }

}
