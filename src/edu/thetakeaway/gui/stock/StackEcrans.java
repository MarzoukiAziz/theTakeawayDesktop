/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui.stock;

import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Synda
 */
public class StackEcrans extends StackPane{
    //Liste d'ecrans a afficher
    public HashMap<String, Node> screens = new HashMap<>();
    
    
    public StackEcrans() {
        super();
    }
    

    //Ajouter un ecran a la collection
    public void addScreen(String name, Node screen){
        screens.put(name, screen);
    }
    
    // Retourne un ecran
    public Node getScreen(String name){
        return screens.get(name);
    }

    public boolean loadScreen(String name, String resource)throws Exception{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dealforall/div/gui/" + resource));
            Parent loadScreen = (Parent) loader.load();
            InterfaceScreenController ScreenControler = ((InterfaceScreenController)loader.getController());
            ScreenControler.setScreenPane(this);
            addScreen(name, loadScreen);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    //This method tries to displayed the screen with a predefined name.
    //First it makes sure the screen has been already loaded.  Then if there is more than
    //one screen the new screen is been added second, and then the current screen is removed.
    // If there isn't any screen being displayed, the new screen is just added to the root.
    
    public boolean setScreen(final String name){
        if (screens.get(name) != null) {   //chargement effectué
      final DoubleProperty opacity = opacityProperty();

      if (!getChildren().isEmpty()) {    //test existance nbrEcrans > 1
        Timeline fade = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
            new KeyFrame(new Duration(200), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                  getChildren().remove(0);                    //remove the displayed screen
                  getChildren().add(0, screens.get(name));     //add the screen
                  Timeline fadeIn = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                    new KeyFrame(new Duration(200), new KeyValue(opacity, 1.0)));
                  fadeIn.play();        }      }, 
            new KeyValue(opacity, 0.0)
                                )
                                );
        fade.play();
        
      } else {      //Afficher en cas contraire
        setOpacity(0.0);
        getChildren().add(screens.get(name));  

        Timeline fadeIn = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
            new KeyFrame(new Duration(200), new KeyValue(opacity, 1.0)));
        fadeIn.play();
      }
      return true;
    } else {
      System.out.println("Le Screen n'a pas été chargé correctement!");
      return false;
    }

}
    
    
    //Supprimer un screen de la collection
    public boolean unloadScreen(String name){
        if(screens.remove(name) == null)
        {    
            System.out.println("Le screen n'existe pas!");
            return false;
        }else{
            return true;
        }
    }
  
}

