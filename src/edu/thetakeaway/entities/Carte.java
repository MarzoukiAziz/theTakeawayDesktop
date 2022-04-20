/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.thetakeaway.entities;

/**
 *
 * @author rafrafi
 */
public class Carte {
    int id;
    private String numero,nomcomplet,datexp,cvv;
    private User userid;

    public Carte() {
    }

    public Carte(int id, String numero, String nomcomplet, String datexp, String cvv, User userid) {
        this.id = id;
        this.numero = numero;
        this.nomcomplet = nomcomplet;
        this.datexp = datexp;
        this.cvv = cvv;
        this.userid = userid;
    }

    public Carte(String numero, String nomcomplet, String datexp, String cvv, User userid) {
        this.numero = numero;
        this.nomcomplet = nomcomplet;
        this.datexp = datexp;
        this.cvv = cvv;
        this.userid = userid;
    }

    public Carte(String numero, String nomcomplet, String datexp, String cvv) {
        this.numero = numero;
        this.nomcomplet = nomcomplet;
        this.datexp = datexp;
        this.cvv = cvv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomcomplet() {
        return nomcomplet;
    }

    public void setNomcomplet(String nomcomplet) {
        this.nomcomplet = nomcomplet;
    }

    public String getDatexp() {
        return datexp;
    }

    public void setDatexp(String datexp) {
        this.datexp = datexp;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Carte{" + "id=" + id + ", numero=" + numero + ", nomcomplet=" + nomcomplet + ", datexp=" + datexp + ", cvv=" + cvv + ", userid=" + userid + '}';
    }
    
    

  
    
   
    
}
