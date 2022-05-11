
package edu.thetakeaway.entities;

import java.sql.Date;
import java.util.Objects;


public class User {
  int id,numtel;
    String nom,prenom,email,password,roles;
    String securityq, answer;
    
    Date date=null;

     public User() {
    }

    public User(int numtel, String nom, String prenom, String email, String password, String securityq, String answer) {
         this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
      
        this.securityq = securityq;
        this.answer = answer;
    }
     
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", numtel=" + numtel + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", roles=" + roles + ", securityq=" + securityq + ", answer=" + answer + '}';
    }

    public User(int numtel, String nom, String prenom, String email, String password, String roles, String securityq, String answer) {
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.securityq = securityq;
        this.answer = answer;
    }

    public User(int id, int numtel, String nom, String prenom, String email, String password, String roles, String securityq, String answer) {
        this.id = id;
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.securityq = securityq;
        this.answer = answer;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public User(int numtel, String nom, String prenom, String email, String password, String roles) {
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(int id, int numtel, String nom, String prenom, String email, String password, String roles) {
        this.id = id;
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(int id) {
        this.id = id;
    }

    public User(int numtel, String nom, String prenom, String email, String password,int id) {
        
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public User(int numtel, String nom, String prenom, String email, String password) {
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
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

    public String getSecurityq() {
        return securityq;
    }

    public void setSecurityq(String securityq) {
        this.securityq = securityq;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    

  

    @Override
    public int hashCode() {
        int hash = 5;
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
        final User other = (User) obj;
        if (this.numtel != other.numtel) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    
}