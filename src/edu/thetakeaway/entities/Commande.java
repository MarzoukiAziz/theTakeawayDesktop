
package edu.thetakeaway.entities;

import java.sql.Date;
import java.util.Objects;

public class Commande {
    int id;
    Restaurant restaurant;
    User client;
    double prix;
    String statut;
    Date date;
    String method;

    public Commande(int id, Restaurant restaurant, User client, double prix, String statut, Date date, String method) {
        this.id = id;
        this.restaurant = restaurant;
        this.client = client;
        this.prix = prix;
        this.statut = statut;
        this.date = date;
        this.method = method;
    }

    public Commande(Restaurant restaurant, User client, double prix, String statut, Date date, String method) {
        this.restaurant = restaurant;
        this.client = client;
        this.prix = prix;
        this.statut = statut;
        this.date = date;
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", restaurant=" + restaurant + ", client=" + client + ", prix=" + prix + ", statut=" + statut + ", date=" + date + ", method=" + method + '}';
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
        final Commande other = (Commande) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.prix) != Double.doubleToLongBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.statut, other.statut)) {
            return false;
        }
        if (!Objects.equals(this.method, other.method)) {
            return false;
        }
        if (!Objects.equals(this.restaurant, other.restaurant)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
    
    
}
