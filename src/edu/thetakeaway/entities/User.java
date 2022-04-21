/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.entities;

/**
 *
 * @author Oumaima
 */
public class User {
    
   private int id;
    private String nom,email,password,roles;
    public String getEmail;

    public User() {
    }

    public User(String nom, String email, String password, String roles) {
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(int id, String nom, String email, String password, String roles, String getEmail) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.getEmail = getEmail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getGetEmail() {
        return getEmail;
    }

    public void setGetEmail(String getEmail) {
        this.getEmail = getEmail;
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", password=" + password + ", roles=" + roles + ", getEmail=" + getEmail + '}';
    }
    
    
}

    

