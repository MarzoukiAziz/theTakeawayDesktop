
package edu.thetakeaway.entities;

import java.util.Objects;

public class ElementDetails {
    int id;
    Menu element;
    Commande cmd;
    int quantite;

    public ElementDetails(int id, Menu element, Commande cmd, int quantite) {
        this.id = id;
        this.element = element;
        this.cmd = cmd;
        this.quantite = quantite;
    }

    public ElementDetails(Menu element, Commande cmd, int quantite) {
        this.element = element;
        this.cmd = cmd;
        this.quantite = quantite;
    }

    public ElementDetails(Menu element, int quantite) {
        this.element = element;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Menu getElement() {
        return element;
    }

    public void setElement(Menu element) {
        this.element = element;
    }

    public Commande getCmd() {
        return cmd;
    }

    public void setCmd(Commande cmd) {
        this.cmd = cmd;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "ElementDetails{" + "id=" + id + ", element=" + element + ", cmd=" + cmd + ", quantite=" + quantite + '}';
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
        final ElementDetails other = (ElementDetails) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.element, other.element)) {
            return false;
        }
        if (!Objects.equals(this.cmd, other.cmd)) {
            return false;
        }
        return true;
    }
    
    
}
