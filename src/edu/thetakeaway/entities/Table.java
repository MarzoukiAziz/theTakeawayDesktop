
package edu.thetakeaway.entities;

public class Table {
    private int id;
    private int posX;
    private int posY;
    private int nbPlaces;
    private int numero;
    private Restaurant restaurant;

    public Table(int id, int posX, int posY, int nbPlaces, int numero, Restaurant restaurant) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.nbPlaces = nbPlaces;
        this.numero = numero;
        this.restaurant = restaurant;
    }

  

    public Table() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Table{" + "id=" + id + ", posX=" + posX + ", posY=" + posY + ", nbPlaces=" + nbPlaces + ", numero=" + numero + ", restaurant=" + restaurant + '}';
    }


    @Override
    public int hashCode() {
        int hash = 7;
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
        final Table other = (Table) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.posX != other.posX) {
            return false;
        }
        if (this.posY != other.posY) {
            return false;
        }
        if (this.nbPlaces != other.nbPlaces) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (this.restaurant != other.restaurant) {
            return false;
        }
        return true;
    }
    
    
}
