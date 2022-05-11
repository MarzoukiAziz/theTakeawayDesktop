/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author USER
 */
public class Commentaire {
    private String id;
    private String author_id;
    private String date;
    private String contenu;
    private String blog_id;

    public Commentaire(String id, String author_id, String date, String contenu, String blog_id) {
        this.id = id;
        this.author_id = author_id;
        this.date = date;
        this.contenu = contenu;
        this.blog_id = blog_id;
    }

    public Commentaire(String author_id, String date, String contenu, String blog_id) {
        this.author_id = author_id;
        this.date = date;
        this.contenu = contenu;
        this.blog_id = blog_id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(String blog_id) {
        this.blog_id = blog_id;
    }
    
    
}
