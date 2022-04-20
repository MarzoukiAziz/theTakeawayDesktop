/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

/**
 *
 * @author moham
 */
public class User {
     private int id;
    private String email;
    private String role;
    private String nom;
    private String prenom;
    private String num_tel;
    private int points;
    private String avatar;
    
    
    public User() {
    }

    public User(int id, String email, String role, String nom, String password, String prenom, String num_tel, int points) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

  

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", role=" + role + ", nom=" + nom +  ", prenom=" + prenom + ", num_tel=" + num_tel + ", points=" + points + '}';
    }
}
