
package edu.thetakeaway.services;

import java.util.List;

public interface IService <T>{
    public void ajouter(T p);
    public void supprimer(int id);
    public void modifier(T p);
    public List<T> getAll();
    
}
