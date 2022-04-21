/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.entities;

import java.sql.Date;

/**
 *
 * @author Oumaima
 */
public class commentaire {
    private int id,author_id,blog_client_id;
    private String date,contenu;

    public commentaire() {
    }

    public commentaire(int id, int author_id, int blog_client_id) {
        this.id = id;
        this.author_id = author_id;
        this.blog_client_id = blog_client_id;
    }

    public commentaire(int id, int author_id, int blog_client_id, String date, String contenu) {
        this.id = id;
        this.author_id = author_id;
        this.blog_client_id = blog_client_id;
        this.date = date;
        this.contenu = contenu;
    }

    public commentaire(String date, String contenu) {
        this.date = date;
        this.contenu = contenu;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getBlog_client_id() {
        return blog_client_id;
    }

    public void setBlog_client_id(int blog_client_id) {
        this.blog_client_id = blog_client_id;
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

    @Override
    public String toString() {
        return "commentaire{" + "author_id=" + author_id + ", blog_client_id=" + blog_client_id + ", date=" + date + ", contenu=" + contenu + '}';
    }
 
    
}
