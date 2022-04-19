
package edu.thetakeaway.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Reponse {
    private int id;
    private Reclamation reclamation;
    private User author;
    private String contenu;
    private Date date;
    private Time heure;

    public Reponse() {
    }

    public Reponse(Reclamation reclamation, User author, String contenu, Date date, Time heure) {
        this.reclamation = reclamation;
        this.author = author;
        this.contenu = contenu;
        this.date = date;
        this.heure = heure;
    }

    public Reponse(int id, Reclamation reclamation, User author, String contenu, Date date, Time heure) {
        this.id = id;
        this.reclamation = reclamation;
        this.author = author;
        this.contenu = contenu;
        this.date = date;
        this.heure = heure;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reponse(User author) {
        this.author = author;
    }


    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
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

  

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
        final Reponse other = (Reponse) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        if (!Objects.equals(this.reclamation, other.reclamation)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
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
        return "Reponse{" + "id=" + id + ", reclamation=" + reclamation + ", author=" + author + ", contenu=" + contenu + ", date=" + date + ", heure=" + heure + '}';
    }

    
}
