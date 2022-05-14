/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.time.LocalTime;

/**
 *
 * @author Synda
 */
public class Facture {
    
    private int id , id_fournisseur, id_ingrediant;
    private String nomf , nomi;
    private Fournisseur fournisseur;
    private Ingrediant ingrediant ;
    private int qunatite;
    private float prix_unitaire ;
    private Date date ;
    private LocalTime heure;
    

    public Facture(String nomf, String nomi, Fournisseur fournisseur, Ingrediant ingrediant, int qunatite, float prix_unitaire, Date date, LocalTime heure) {
        this.nomf = nomf;
        this.nomi = nomi;
        this.fournisseur = fournisseur;
        this.ingrediant = ingrediant;
        this.qunatite = qunatite;
        this.prix_unitaire = prix_unitaire;
        this.date = date;
        this.heure = heure;
    }

    public Facture(int id_fournisseur, int id_ingrediant, int qunatite, float prix_unitaire, Date date, LocalTime heure) {
        this.id_fournisseur = id_fournisseur;
        this.id_ingrediant = id_ingrediant;
        this.qunatite = qunatite;
        this.prix_unitaire = prix_unitaire;
        this.date = date;
        this.heure = heure;
    }

    public Facture(String nomf, String nomi, int qunatite, float prix_unitaire, Date date, LocalTime heure) {
        this.nomf = nomf;
        this.nomi = nomi;
        this.qunatite = qunatite;
        this.prix_unitaire = prix_unitaire;
        this.date = date;
        this.heure = heure;
    }
    
    
    

    public Facture(int id, Fournisseur fournisseur, Ingrediant ingrediant, int qunatite, float prix_unitaire, Date date, LocalTime heure) {
        this.id = id;
        this.fournisseur = fournisseur;
        this.ingrediant = ingrediant;
        this.qunatite = qunatite;
        this.prix_unitaire = prix_unitaire;
        this.date = date;
        this.heure = heure;
    }

    public Facture(Fournisseur fournisseur, Ingrediant ingrediant, int qunatite, float prix_unitaire, Date date, LocalTime heure) {
        this.fournisseur = fournisseur;
        this.ingrediant = ingrediant;
        this.qunatite = qunatite;
        this.prix_unitaire = prix_unitaire;
        this.date = date;
        this.heure = heure;
    }

    public Facture(int id_fournisseur, int id_ingrediant, int qunatite, float prix_unitaire, Date date) {
        this.id_fournisseur = id_fournisseur;
        this.id_ingrediant = id_ingrediant;
        this.qunatite = qunatite;
        this.prix_unitaire = prix_unitaire;
        this.date = date;
    }

    public Facture(Fournisseur p1, Ingrediant p2, int i, Date date, Date date0) {
    }

    public Facture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public int getId_ingrediant() {
        return id_ingrediant;
    }

    public void setId_ingrediant(int id_ingrediant) {
        this.id_ingrediant = id_ingrediant;
    }

    
    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Ingrediant getIngrediant() {
        return ingrediant;
    }

    public void setIngrediant(Ingrediant ingrediant) {
        this.ingrediant = ingrediant;
    }

    public int getQunatite() {
        return qunatite;
    }

    public void setQunatite(int qunatite) {
        this.qunatite = qunatite;
    }

    public float getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(float prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", id_fournisseur=" + id_fournisseur + ", id_ingrediant=" + id_ingrediant + ", qunatite=" + qunatite + ", prix_unitaire=" + prix_unitaire + ", date=" + date + ", heure=" + heure + '}'+"\n";
    }

    
    
    
    
    
    
}
