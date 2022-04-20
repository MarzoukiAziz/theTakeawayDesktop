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
public class CommandeElement {
    private int id;
    private int element_id;
    private int commande_id;
    private int quantite;

    public CommandeElement() {
    }

    public CommandeElement(int id, int element_id, int commande_id, int quantite) {
        this.id = id;
        this.element_id = element_id;
        this.commande_id = commande_id;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getElement_id() {
        return element_id;
    }

    public void setElement_id(int element_id) {
        this.element_id = element_id;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "CommandeElement{" + "id=" + id + ", element_id=" + element_id + ", commande_id=" + commande_id + ", quantite=" + quantite + '}';
    }
    
    
}
