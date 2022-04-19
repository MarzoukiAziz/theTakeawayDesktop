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
    int id,userid;
    String numero,nomcomplet,datexp,cvv;

    public Carte() {
    }

    public Carte(int id, int userid, String numero, String nomcomplet, String datexp, String cvv) {
        this.id = id;
        this.userid = userid;
        this.numero = numero;
        this.nomcomplet = nomcomplet;
        this.datexp = datexp;
        this.cvv = cvv;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    @Override
    public String toString() {
        return "Carte{" + "id=" + id + ", userid=" + userid + ", numero=" + numero + ", nomcomplet=" + nomcomplet + ", datexp=" + datexp + ", cvv=" + cvv + '}';
    }
    
    
}
