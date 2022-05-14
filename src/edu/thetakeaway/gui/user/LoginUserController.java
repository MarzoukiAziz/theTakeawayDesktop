/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.thetakeaway.gui.user;

import com.mysql.jdbc.PreparedStatement;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.services.UserServices;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author rafrafi
 */
public class LoginUserController implements Initializable {

    public static String password;
    Connection cnx;
    PreparedStatement pst;
    ResultSet rs;
    UserServices us = new UserServices();

    @FXML
    private Button btnLog;
    @FXML
    private TextField tfPass;
    @FXML
    private TextField tfUser;
    @FXML
    private Button btnInscri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void connection(ActionEvent event) throws IOException {
        UserServices us = new UserServices();
        password = tfPass.getText();
        if (tfUser.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Saisir votre adresse email SVP!", ButtonType.OK);
            a.showAndWait();
            return;
        }
        if (tfPass.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Saisir votre mot de passe SVP!", ButtonType.OK);
            a.showAndWait();
            return;
        }

        String email = tfUser.getText();
        String pass = tfPass.getText();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/thetakeaway", "root", "");
            pst = (PreparedStatement) cnx.prepareStatement("Select * from client where email=? and password=?");

            pst.setString(1, email);
            boolean l = us.verifPassword(email, pass);
            pst.setString(2, pass);

            rs = pst.executeQuery();
            if (l == true) {
                if (us.checkRole(email).equals("USER")) {
                    String tilte = "Sign In";
                    String message = tfUser.getText();
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle(tilte);
                    tray.setMessage(message);
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(1000));

                    UserServices c1 = new UserServices();
                    SharedData da = new SharedData();
                    User us1 = new User();
                    us1 = c1.getByEmail(email);
                    da.currentUser = us1;
                    da.CurrentUserId = us1.getId();
                    if (c1.checkRole(us1.getEmail()) == "ADMIN") {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../dashboard/AdminDashboardScreen.fxml"));
                        Parent root = loader.load();
                        tfUser.getScene().setRoot(root);
                    } else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../dashboard/UserDashboardScreen.fxml"));
                        Parent root = loader.load();
                        tfUser.getScene().setRoot(root);

                    }

                } else {

                    String tilte = "Sign In";
                    String message = tfUser.getText();
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle(tilte);
                    tray.setMessage(message);
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(1000));

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../dashboard/AdminDashboardScreen.fxml"));

                    Parent root = loader.load();
                    tfUser.getScene().setRoot(root);
                    UserServices c1 = new UserServices();
                    SharedData da = new SharedData();
                    User us1 = new User();
                    us1 = c1.getByEmail(email);

                    da.CurrentUserId = us1.getId();

                }

            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Echec Connexion", ButtonType.OK);
                a.showAndWait();

                tfPass.setText("");
                tfUser.requestFocus();
                String tilte = "Sign In";
                String message = "Error Username " + "'" + tfUser.getText() + "'" + " Wrong";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.millis(3000));

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void openSignup(ActionEvent actionEvent) {
        navigateTo(actionEvent, "InscriptionUser.fxml");
    }

    @FXML
    private void openForget(ActionEvent event) throws IOException {
        navigateTo(event, "ForgotPass.fxml");
    }

    private void navigateTo(ActionEvent actionEvent, String path) {
        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource(path));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            second_stage.setScene(ex_section_scene);
            second_stage.show();
        } catch (IOException ex) {

        }
    }

}
