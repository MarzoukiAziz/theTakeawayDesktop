/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

/**
 *
 * @author Bouga
 */
public class menu_element {
    private int id ,prix ; 
    private String nom , description , categorie ;
    
    private String image ; 
    private array options ;

    private static class array {

        public array() {
        }
    }

    public menu_element(int id, int prix, String nom, String description, String categorie, String image, array options) {
        this.id = id;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.image = image;
        this.options = options;
    }

    public menu_element(int prix, String nom, String description, String categorie, String image, array options) {
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.image = image;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public array getOptions() {
        return options;
    }

    public void setOptions(array options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "menu_element{" + "id=" + id + ", prix=" + prix + ", nom=" + nom + ", description=" + description + ", categorie=" + categorie + ", image=" + image + ", options=" + options + '}';
    }
    
}
