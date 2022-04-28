/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc.entities;

/**
 *
 * @author Synda
 */
public class Restaurant {
    private int id;
    private String nom;

    public Restaurant(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Restaurant(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    public Restaurant() {
    }

    public Restaurant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
