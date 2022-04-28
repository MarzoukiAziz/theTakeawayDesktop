
package edu.thetakeaway.entities;

import java.sql.Date;
import java.util.Objects;

public class Promotion {
    int id;
    Menu element;
    Date dateDebut;
    Date dateFin;
    double prixPromo;
    String banner;

    public Promotion(int id, Menu element, Date dateDebut, Date dateFin, double prixPromo, String banner) {
        this.id = id;
        this.element = element;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixPromo = prixPromo;
        this.banner = banner;
    }

    public Promotion(Menu element, Date dateDebut, Date dateFin, double prixPromo, String banner) {
        this.element = element;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixPromo = prixPromo;
        this.banner = banner;
    }

    public int getId() {
        return id;
    }

    public Menu getElement() {
        return element;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public double getPrixPromo() {
        return prixPromo;
    }

    public String getBanner() {
        return banner;
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
        final Promotion other = (Promotion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.prixPromo) != Double.doubleToLongBits(other.prixPromo)) {
            return false;
        }
        if (!Objects.equals(this.banner, other.banner)) {
            return false;
        }
        if (!Objects.equals(this.element, other.element)) {
            return false;
        }
        if (!Objects.equals(this.dateDebut, other.dateDebut)) {
            return false;
        }
        if (!Objects.equals(this.dateFin, other.dateFin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", element=" + element + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", prixPromo=" + prixPromo + ", banner=" + banner + '}';
    }
    
    
}
