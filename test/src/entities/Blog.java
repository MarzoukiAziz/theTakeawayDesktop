/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author USER
 */
public class Blog {
    private String id;
    private String author_id;
    private String title;
    private String contenu;
    private String date;
    private String statut;
    
    public Blog() {
    }

    public Blog(String id, String author_id, String title, String contenu, String date, String statut) {
        this.id = id;
        this.author_id = author_id;
        this.title = title;
        this.contenu = contenu;
        this.date = date;
        this.statut = statut;
    }

    public Blog(String author_id, String title, String contenu, String date, String statut) {
        this.author_id = author_id;
        this.title = title;
        this.contenu = contenu;
        this.date = date;
        this.statut = statut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    
}
