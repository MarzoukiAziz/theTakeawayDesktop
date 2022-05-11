/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.thetakeaway.services;

import edu.thetakeaway.entities.User;
import static edu.thetakeaway.gui.user.LoginUserController.password;
import edu.thetakeaway.utils.BCrypt;
import edu.thetakeaway.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author rafrafi
 */
public class UserServices
        implements IService<User> {

    PreparedStatement store;
    User user = new User();
    Connection cnx = DataSource.getInstance().getCnx();

    public String checkRole(String email) {
        String default_return = "roles not found";
        try {
            String req;
            req = "select roles from client where email=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                if (rs.getString("roles").equals("[\"ROLE_ADMIN\"]")) {

                    return "ADMIN";
                } else if (rs.getString("roles").equals("[\"ROLE_USER\"]")) {
                    System.out.println("third");
                    return "USER";

                }

            }

        } catch (SQLException ex) {
        }
        return default_return;
    }

    @Override
    public void ajouter(User p) {
        try {
            String req = "INSERT INTO  `client` (`email`, `password`,`nom`,`prenom`,`num_tel`, `roles`) VALUES (?,?,?,?,?,?)";
            String password = BCrypt.hashpw(p.getPassword(), BCrypt.gensalt());
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getEmail());

            ps.setString(2, password);

            ps.setString(3, p.getNom());
            ps.setString(4, p.getPrenom());
            ps.setInt(5, p.getNumtel());
            ps.setString(6, "[\"ROLE_USER\"]");

            ps.executeUpdate();
            System.out.println("User ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void ajouter2(User p) {
        try {
            String req = "INSERT INTO  `client` (`email`, `password`,`nom`,`prenom`,`num_tel`, `roles`, `securityq`, `answer`) VALUES (?,?,?,?,?,?,?,?)";
            String password = BCrypt.hashpw(p.getPassword(), BCrypt.gensalt());
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getEmail());

            ps.setString(2, password);

            ps.setString(3, p.getNom());
            ps.setString(4, p.getPrenom());
            ps.setInt(5, p.getNumtel());
            ps.setString(6, "[\"ROLE_USER\"]");
            ps.setString(7, p.getSecurityq());
            ps.setString(8, p.getAnswer());
            ps.executeUpdate();
            System.out.println("User ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifieree(int id, User p) {
        try {
            PreparedStatement ps = cnx.prepareStatement("UPDATE client SET `num_tel`=? , `nom`= ? , `prenom`= ? , `email`=?, `password`=  ? WHERE id=  '" + id + "'");
            ps.setInt(1, p.getNumtel());
            ps.setString(2, p.getNom());
            ps.setString(3, p.getPrenom());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getPassword());

            if (ps.executeUpdate() > 0) {

                JOptionPane.showMessageDialog(null, "User Updated", "Edit User", JOptionPane.INFORMATION_MESSAGE);
                //System.out.println("Product Updated");
            } else {
                JOptionPane.showMessageDialog(null, "User Not Updated", "Edit User", JOptionPane.ERROR_MESSAGE);
                //System.out.println("Some Error Message Here");  
            }
            System.out.println("User Modifieé avec succées ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier2(int id, User r) {
        try {
            String password = BCrypt.hashpw(r.getPassword(), BCrypt.gensalt());
            String req = "UPDATE `client` SET `email` = '" + r.getEmail()
                    + "', `password` = '" + password
                    + "', `nom` = '" + r.getNom()
                    + "', `prenom` = '" + r.getPrenom()
                    + "', `num_tel` = '" + r.getNumtel()
                    + "', `roles` = '" + "[\"ROLE_USER\"]"
                    + "', `securityq` = '" + r.getSecurityq()
                    + "', `answer` = '" + r.getAnswer()
                    + "' WHERE `client`.`id` = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);

            System.out.println("User Modifieé avec succées ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static boolean isValidPhoneNumber(String phone_number) {
        boolean isValid = phone_number.matches("\\d{8}");
        System.out.println(phone_number + " : " + isValid);
        return isValid;
    }

    public boolean SendMail(User user, String code) {

        String from, to, host, sub, content;
        from = "pidevers3a10@gmail.com";
        to = user.getEmail();
        host = "localhost";
        if (code == "null") {
            sub = "Bienvenue sur notre Plateforme";
            content = "Bonjour Mr/Mme " + user.getNom() + ". Au nom de tous les membres du plateforme, je vous souhaite la bienvenue.";
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            }
            );
            try {
                MimeMessage m = new MimeMessage(session);
                m.setFrom(new InternetAddress(from));
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                m.setSubject(sub);
                m.setText(content);
                System.out.println("Messsaaeem");
                System.out.println(m);
                Transport.send(m);
                return true;

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            sub = "Réinitialisation du mot de passe de votre compte ";
            content = "Bonjour" + user.getNom() + ".\n \n Avez-vous oublié votre mot de passe \n \n Taper ce code dans l'application =  " + code + " \n \n"
                    + "Si vous ne souhaitez pas changer votre mot de passe ou si vous ne l’avez pas demandé, veuillez ignorer et supprimer ce message. \n \n"
                    + "Cordialement,\n \n "
                    + "L’équipe PiDevers ";
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            }
            );
            try {
                MimeMessage m = new MimeMessage(session);
                m.setFrom(new InternetAddress(from));
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                m.setSubject(sub);
                m.setText(content);
                Transport.send(m);
                return true;

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public boolean isValidAnswe(int phone_number, String ans) {
        User t = new User();
        String l = "";
        boolean res = false;

        try {

            String req = ("Select * from `client` WHERE num_tel = " + phone_number);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            rs.next();
            t.setId(rs.getInt("id"));
            t.setNom(rs.getString("nom"));
            t.setEmail(rs.getString("email"));
            t.setPassword(rs.getString("password"));
            t.setSecurityq(rs.getString("securityq"));
            t.setAnswer(rs.getString("answer"));
            t.setNumtel(rs.getInt("num_tel"));
            l = t.getAnswer();
            System.out.println(l);

            if (l.equals(ans)) {

                res = true;
                return res;
            } else {

                res = false;
                return res;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return res;
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `client` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User p) {
        try {

            PreparedStatement ps = cnx.prepareStatement("UPDATE client SET `numtel`=? , `nom`= ? , `prenom`= ? , `email`=, `password`=  ? WHERE id=  '" + p.getId() + "'");
            ps.setInt(5, p.getNumtel());
            ps.setString(3, p.getNom());
            ps.setString(4, p.getPrenom());
            ps.setString(1, p.getEmail());
            ps.setString(2, p.getPassword());

            System.out.println("User Modifieé avec succées ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            String req = "Select * from client";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setNumtel(rs.getInt("num_tel"));

                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public User login(String username, String mdp) {
        User u = new User();

        try {
            //
            String requete = "SELECT * from client where userName= ? AND mdp= ?";
            System.out.println(username + mdp);
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, username);
            ps.setString(2, mdp);
            int id = 0;
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;

    }

    public User getById(int id) {
        User t = new User();

        try {

            String req = ("Select * from `client` WHERE id = " + id);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            rs.next();
            t.setId(rs.getInt("id"));
            t.setNom(rs.getString("nom"));
            t.setEmail(rs.getString("email"));
            t.setPassword(rs.getString("password"));
            t.setSecurityq(rs.getString("securityq"));
            t.setAnswer(rs.getString("answer"));
            t.setNumtel(rs.getInt("num_tel"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }

    public User getUserByIda(int id) {
        User t = new User();

        try {

            String req = ("Select * from `client` WHERE id = " + id);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            rs.next();
            t.setId(rs.getInt("id"));
            t.setNom(rs.getString("nom"));
            t.setEmail(rs.getString("email"));
            t.setPassword(rs.getString("password"));
            t.setSecurityq(rs.getString("securityq"));
            t.setAnswer(rs.getString("answer"));
            t.setNumtel(rs.getInt("num_tel"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }

    public User getByIda(int id) {
        User t = new User();

        try {

            String req = ("Select * from `client` WHERE num_tel = " + id);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            rs.next();
            t.setId(rs.getInt("id"));
            t.setNom(rs.getString("nom"));
            t.setEmail(rs.getString("email"));
            t.setPassword(rs.getString("password"));
            t.setSecurityq(rs.getString("securityq"));
            t.setAnswer(rs.getString("answer"));
            t.setNumtel(rs.getInt("num_tel"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }

    public User getByEmail(String mail) {
        User t = new User();
        int idd = 0;

        try {

            String req = ("Select id from `client` WHERE `email` = '" + mail + "'");
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            rs.next();

            t.setId(rs.getInt("id"));
            t.setNom(rs.getString("nom"));
            t.setEmail(rs.getString("email"));
            t.setPassword(rs.getString("password"));
            t.setSecurityq(rs.getString("securityq"));
            t.setAnswer(rs.getString("answer"));
            t.setNumtel(rs.getInt("num_tel"));
            idd = t.getId();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }

    public boolean verifPassword(String nom, String password) {
        try {
            Statement ste = cnx.createStatement();

            ResultSet rs = ste.executeQuery("select e.* from client e where email='" + nom + "'");

            while (rs.next()) {
                String passBase = rs.getString("password");
                if (BCrypt.checkpw(password, passBase)) {
                    return true;
                } else {
                    return false;
                }

            }

        } catch (SQLException sq) {
            return false;
        }
        return false;
    }

    public int existeMail2(String email) {
        try {
            Statement s = cnx.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(*) from client WHERE email ='" + email + "'");
            int size = 0;

            rs.next();

            size = rs.getInt(1);

            return size;
        } catch (Exception ex) {
            System.out.println("error");
        }
        return 0;
    }

    public int existeMailnum(String email, int num) {
        try {
            Statement s = cnx.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(*) from client WHERE email ='" + email + "' and num_tel='" + num + "'");
            int size = 0;

            rs.next();

            size = rs.getInt(1);

            return size;
        } catch (Exception ex) {
            System.out.println("error");
        }
        return 0;
    }

    public void modifierPass(int p, String pass) {
        String s = String.valueOf(p);
        try {

            PreparedStatement ps = cnx.prepareStatement("UPDATE client SET   `password`=  ? WHERE num_tel= ? ");

            ps.setString(1, pass);
            ps.setString(2, s);

            if (ps.executeUpdate() > 0) {

                JOptionPane.showMessageDialog(null, "User Updated", "Edit User", JOptionPane.INFORMATION_MESSAGE);
                //System.out.println("Product Updated");
            } else {
                JOptionPane.showMessageDialog(null, "User Not Updated", "Edit User", JOptionPane.ERROR_MESSAGE);
                //System.out.println("Some Error Message Here");  
                ps.close();
            }
            System.out.println("User Modifieé avec succées ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int NumberUsers() throws SQLException {
        int Compte = 0;
        String Num = "";
        String req = "SELECT COUNT(id) FROM client As Compte  ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Compte = rs.getInt(1) + 1;

        }
        System.out.println(Compte);
        return Compte;
    }

    public int NumberAmin() throws SQLException {
        int Compte = 0;
        String Num = "";
        String req = "SELECT COUNT(roles) FROM client As Compte where roles = \"[\\\"ROLE_ADMIN\\\"]\"";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Compte = rs.getInt(1) + 1;

        }
        System.out.println(Compte);
        return Compte;
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int existeNumm(int email) {
        try {
            Statement s = cnx.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(*) from client WHERE num_tel ='" + email + "'");
            int size = 0;

            rs.next();

            size = rs.getInt(1);

            return size;
        } catch (Exception ex) {
            System.out.println("error");
        }
        return 0;
    }

    public boolean isValidString(String email) {
        boolean result = true;

        if (Pattern.matches("[a-zA-Z]+", email)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

}
