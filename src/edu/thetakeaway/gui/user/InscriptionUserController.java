/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.thetakeaway.gui.user;

import edu.thetakeaway.entities.User;
import edu.thetakeaway.services.UserServices;
import edu.thetakeaway.utils.Mail;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
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
public class InscriptionUserController implements Initializable {

    @FXML
    private TextField tfNum;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfMail;
    @FXML
    private TextField tfMdp;
    @FXML
    private TextField tfMdp2;

    @FXML
    private TextField Ianswer;

    public static String password;
    @FXML
    private Button inscribtn;
    @FXML
    private Button btnlogin;

    @FXML
    private ChoiceBox<String> ISecurty;
    private String[] choix = {"In what city were you born?", "What is the name of your favorite pet?", "What is your mother's maiden name?", "What high school did you attend?", "What was your favorite food as a child?"};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ISecurty.getItems().addAll(choix);

    }

    @FXML
    private void Inscription(ActionEvent event) throws IOException {
        UserServices rs = new UserServices();
        String Numberr = tfNum.getText();
        int numtel = Integer.parseInt(tfNum.getText());
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String Email = tfMail.getText();
        String mdp = tfMdp.getText();
        String password = tfMdp.getText();
        String mdp2 = tfMdp2.getText();
        String qes = ISecurty.getValue();
        String ans = Ianswer.getText();

        System.out.println(mdp2);
        System.out.println(mdp);

        // if (tfNum.getText().isEmpty()|| tfNom.getText().isEmpty()|| tfPrenom.getText().isEmpty()|| tfMail.getText().isEmpty()||tfMdp.getText().isEmpty())
        //  {
        //      Alert a=new Alert(Alert.AlertType.ERROR,"les champs sont invalides!", ButtonType.OK);
//           a.showAndWait();
//       }else {
//           ServiceUser rs = new ServiceUser();
//           User u = new User(Integer.getInteger(tfNum.getText()), tfNom.getText(), tfPrenom.getText(), tfMail.getText(), tfMdp.getText());
//           rs.ajouter(u);
//           Alert a1=new Alert(Alert.AlertType.INFORMATION, "ajouté",ButtonType.OK);
//           a1.showAndWait();
//        }
//    }
        int h = rs.existeMail2(Email);
        if (tfNom.getText().isEmpty() || tfPrenom.getText().isEmpty() || tfMail.getText().isEmpty() || tfMdp.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "les champs sont vides!", ButtonType.OK);
            a.showAndWait();
        } else if (rs.existeNumm(numtel) != 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, " Le Numero deja exist", ButtonType.OK);
            a.showAndWait();
            tfNum.setText("");

            tfNum.requestFocus();
        } else if (rs.isNumeric(Numberr) == false) {
            Alert a = new Alert(Alert.AlertType.ERROR, " Verifyer Numero deja exist", ButtonType.OK);
            a.showAndWait();
            tfNum.setText("");

            tfNum.requestFocus();
        } else if (rs.isValidPhoneNumber(Numberr) != true) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Numero", ButtonType.OK);
            a.showAndWait();
            tfNum.setText("");

            tfNum.requestFocus();
        } else if (rs.isValidString(nom) == false) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre nom", ButtonType.OK);
            a.showAndWait();
            tfNom.setText("");

            tfNom.requestFocus();
        } else if (rs.isValidString(prenom) == false) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Prenom", ButtonType.OK);
            a.showAndWait();
            tfPrenom.setText("");

            tfPrenom.requestFocus();
        } else if (h != 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Le Email est deja utuliser", ButtonType.OK);
            a.showAndWait();
            tfMail.setText("");

            tfMail.requestFocus();

        } else if (!(mdp2.equals(mdp))) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Password", ButtonType.OK);
            a.showAndWait();
            tfMdp.setText("");
            tfMdp2.setText("");
            tfMdp.requestFocus();
        } else {

            User u = new User(numtel, nom, prenom, Email, mdp, qes, ans);
            System.out.println("=------------------Test=--------------------");
            System.out.println(u);
            System.out.println("=------------------Test=--------------------");
            rs.ajouter2(u);
            if ((validateEmaill())) {
                String tilte = "Inscription  Success ";
                String message = "un message a etes envoyer ";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(1000));
                Mail m = new Mail();
                String sub = "Bienvenue sur notre thetakway";
                String sub2 = " Votre Mot de pass est: " + u.getPassword();
                String content = "Bonjour Mr/Mme " + u.getNom() + ". Au nom de tous les membres du thetakway, je vous souhaite la bienvenue.";
                String finalcontant = sub + content + sub2;
                m.sendEmail(u.getEmail(), finalcontant);

                navigateToLogin(event);

            }
        }
    }

    @FXML
    private void openlogin(ActionEvent event) throws IOException {
        navigateTo(event, "LoginUser.fxml");

    }

    private void Alerterr() {
        Alert alert = new Alert(AlertType.ERROR);

        // alert.setHeaderText("Results:");
        alert.setContentText("Verifier les champs (tél 8 chiffres) ");
        alert.showAndWait();
    }

    private boolean validateEmaill() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(tfMail.getText());
        if (m.find() && m.group().equals(tfMail.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Email");
            alert.showAndWait();

            return false;
        }
    }

    private void navigateToLogin(ActionEvent actionEvent) {
        navigateTo(actionEvent, "LoginUser.fxml");
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
