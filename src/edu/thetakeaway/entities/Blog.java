
package edu.thetakeaway.entities;

import java.sql.Date;



public class Blog {
    private int id;
    private String author_id;
    private String title;
    private String contenu;
    private Date date;
    private String statut;
    
    public Blog() {
    }

    public Blog(int id, String author_id, String title, String contenu, Date date, String statut) {
        this.id = id;
        this.author_id = author_id;
        this.title = title;
        this.contenu = contenu;
        this.date = date;
        this.statut = statut;
    }

    public Blog(String author_id, String title, String contenu, Date date, String statut) {
        this.author_id = author_id;
        this.title = title;
        this.contenu = contenu;
        this.date = date;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
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
    
    
}
