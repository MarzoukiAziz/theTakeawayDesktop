
package edu.thetakeaway.entities;

import java.sql.Date;
import java.util.Objects;

public class Blog {
    int id;
    User author;
    String title;
    String contenu;
    Date date;
    String statut;
    String image;

    public Blog(int id, User author, String title, String contenu, Date date, String statut, String image) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.contenu = contenu;
        this.date = date;
        this.statut = statut;
        this.image = image;
    }

    public Blog(User author, String title, String contenu, Date date, String statut, String image) {
        this.author = author;
        this.title = title;
        this.contenu = contenu;
        this.date = date;
        this.statut = statut;
        this.image = image;
    }

    public Blog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", author=" + author + ", title=" + title + ", contenu=" + contenu + ", date=" + date + ", statut=" + statut + ", image=" + image + '}';
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
        final Blog other = (Blog) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        if (!Objects.equals(this.statut, other.statut)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
}
