
package edu.thetakeaway.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;


public class Reclamation {
    private int id;
    private User user;
    private String sujet;
    private String contenu;
    private String statut;
    private Date date;
    private Time heure;

    public Reclamation() {
    }

    public Reclamation(int id, User user, String sujet, String contenu, String statut, Date date, Time heure) {
        this.id = id;
        this.user = user;
        this.sujet = sujet;
        this.contenu = contenu;
        this.statut = statut;
        this.date = date;
        this.heure = heure;
    }

    public Reclamation(User user, String sujet, String contenu, String statut, Date date, Time heure) {
        this.user = user;
        this.sujet = sujet;
        this.contenu = contenu;
        this.statut = statut;
        this.date = date;
        this.heure = heure;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
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

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
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
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.user != other.user) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        if (!Objects.equals(this.statut, other.statut)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.heure, other.heure)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", user=" + user + ", sujet=" + sujet + ", contenu=" + contenu + ", statut=" + statut + ", date=" + date + ", heure=" + heure + '}';
    }
    
}
