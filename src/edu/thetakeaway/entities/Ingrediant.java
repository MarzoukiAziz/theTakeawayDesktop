/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.entities;


import edu.thetakeaway.entities.Restaurant;
import java.util.Objects;

/**
 *
 * @author Synda
 */
public class Ingrediant {
    private Restaurant restaurant;
    private int id,quantite;
    private String nom;

    public Ingrediant(Restaurant restaurant, int id, int quantite, String nom) {
        this.restaurant = restaurant;
        this.id = id;
        this.quantite = quantite;
        this.nom = nom;
    }

    public Ingrediant(Restaurant restaurant, int quantite, String nom) {
        this.restaurant = restaurant;
        this.quantite = quantite;
        this.nom = nom;
    }

    public Ingrediant() {
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Ingrediant{" + "restaurant=" + restaurant + ", id=" + id + ", quantite=" + quantite + ", nom=" + nom + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Ingrediant other = (Ingrediant) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.restaurant, other.restaurant)) {
            return false;
        }
        return true;
    }
    
  
}
