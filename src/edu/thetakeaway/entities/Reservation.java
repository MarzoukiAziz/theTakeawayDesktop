
package edu.thetakeaway.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reservation {
    private int id;
    private User client;
    private Admin adminCharge;
    private Restaurant restaurant;
    private Date date;
    private Time heureArrive;
    private Time heureDepart;
    private int nbPersonne;
    private String statut;
    private ArrayList<Table> tables;

    public Reservation() {
    }

    public Reservation(User client, Admin adminCharge, Restaurant restaurant, Date date, Time heureArrive, Time heureDepart, int nbPersonne, String statut) {
        this.client = client;
        this.adminCharge = adminCharge;
        this.restaurant = restaurant;
        this.date = date;
        this.heureArrive = heureArrive;
        this.heureDepart = heureDepart;
        this.nbPersonne = nbPersonne;
        this.statut = statut;
    }

    public Reservation(int id, User client, Admin adminCharge, Restaurant restaurant, Date date, Time heureArrive, Time heureDepart, int nbPersonne, String statut, ArrayList<Table> tables) {
        this.id = id;
        this.client = client;
        this.adminCharge = adminCharge;
        this.restaurant = restaurant;
        this.date = date;
        this.heureArrive = heureArrive;
        this.heureDepart = heureDepart;
        this.nbPersonne = nbPersonne;
        this.statut = statut;
        this.tables = tables;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Admin getAdminCharge() {
        return adminCharge;
    }

    public void setAdminCharge(Admin adminCharge) {
        this.adminCharge = adminCharge;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeureArrive() {
        return heureArrive;
    }

    public void setHeureArrive(Time heureArrive) {
        this.heureArrive = heureArrive;
    }

    public Time getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Time heureDepart) {
        this.heureDepart = heureDepart;
    }

    public int getNbPersonne() {
        return nbPersonne;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", client=" + client + ", adminCharge=" + adminCharge + ", restaurant=" + restaurant + ", date=" + date + ", heureArrive=" + heureArrive + ", heureDepart=" + heureDepart + ", nbPersonne=" + nbPersonne + ", statut=" + statut + ", tables=" + tables + '}';
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
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nbPersonne != other.nbPersonne) {
            return false;
        }
        if (!Objects.equals(this.statut, other.statut)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.adminCharge, other.adminCharge)) {
            return false;
        }
        if (!Objects.equals(this.restaurant, other.restaurant)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.heureArrive, other.heureArrive)) {
            return false;
        }
        if (!Objects.equals(this.heureDepart, other.heureDepart)) {
            return false;
        }
        if (!Objects.equals(this.tables, other.tables)) {
            return false;
        }
        return true;
    }

   
}
