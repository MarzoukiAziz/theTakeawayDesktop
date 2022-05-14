/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.entities;

import java.sql.Date;


public class Commentaire {
    private String id;
    private String author_id;
    private Date date;
    private String contenu;
    private int blog_id;

    public Commentaire(String id, String author_id, Date date, String contenu, int blog_id) {
        this.id = id;
        this.author_id = author_id;
        this.date = date;
        this.contenu = contenu;
        this.blog_id = blog_id;
    }

    public Commentaire(String author_id, Date date, String contenu, int blog_id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }
    
    
}
