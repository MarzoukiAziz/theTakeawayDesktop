/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.entities;


import java.sql.Date;

/**
 *
 * @author abdelazizmezri
 */
public class Blog_client {
    private int id;
    private String title,contenu,statut,image,date;
private User u;

    public Blog_client() {
    }

    public Blog_client(int id, String title, String contenu, String statut, String image, String date,User us) {
        this.id = id;
     
        this.title = title;
        this.contenu = contenu;
        this.statut = statut;
        this.image = image;
        this.date = date;
        this.u.setId(us.getId());
    }

    public Blog_client(int id) {
        this.id = id;
 
    }

    public Blog_client( String title, String contenu, String statut, String image, String date,User us) {
    
        this.title = title;
        this.contenu = contenu;
        this.statut = statut;
        this.image = image;
        this.date = date;
         this.u.setId(us.getId());
    }

   
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    @Override
    public String toString() {
        return "Blog_client{" + "title=" + title + ", contenu=" + contenu + ", statut=" + statut + ", image=" + image + ", date=" + date + ", u=" + u + '}';
    }

 
  

  

   
   

   
    
    
}
