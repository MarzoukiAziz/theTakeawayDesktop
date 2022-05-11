/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.thetakeaway.entities;

import java.util.Objects;

/**
 *
 * @author rafrafi
 */
public class Carte {
    int id,Numero,cvv;
    String nom,datexp;
    User user;

    public Carte() {
    }

    public Carte(int id, int Numero, String nom, String datexp, int cvv, User user) {
        this.id = id;
        this.Numero = Numero;
        this.nom = nom;
        this.datexp = datexp;
        this.cvv = cvv;
        this.user = user;
    }

    public Carte(int Numero, int cvv, String nom, String datexp) {
        this.Numero = Numero;
        this.cvv = cvv;
        this.nom = nom;
        this.datexp = datexp;
    }
    public Carte(int Numero, int cvv, String nom, String datexp, User user) {
        this.Numero = Numero;
        this.cvv = cvv;
        this.nom = nom;
        this.datexp = datexp;
        this.user = user;
    }

    public Carte(int Numero, String nom, String datexp, int cvv, User user) {
        this.Numero = Numero;
        this.nom = nom;
        this.datexp = datexp;
        this.cvv = cvv;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDatexp() {
        return datexp;
    }

    public void setDatexp(String datexp) {
        this.datexp = datexp;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "carte{" + "id=" + id + ", Numero=" + Numero + ", nom=" + nom + ", datexp=" + datexp + ", cvv=" + cvv + ", user=" + user + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carte other = (Carte) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.Numero, other.Numero)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.datexp, other.datexp)) {
            return false;
        }
        if (!Objects.equals(this.cvv, other.cvv)) {
            return false;
        }
        return Objects.equals(this.user, other.user);
    }
    
    
}
