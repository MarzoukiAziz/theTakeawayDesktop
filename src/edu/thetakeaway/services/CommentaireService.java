package edu.thetakeaway.services;

import edu.thetakeaway.entities.Blog;
import edu.thetakeaway.entities.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.events.Comment;
import edu.thetakeaway.utils.DataSource;


/**
 *
 * @author USER
 */
public class CommentaireService {
    Connection cnx = DataSource.getInstance().getCnx();
           
    public void ajouter(Commentaire t) {
		try {
                
            String requete = "INSERT INTO commentaire (author_id, contenu, date, blog_client_id) VALUES (?, ?, NOW(), ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, "1");
            pst.setString(2, t.getContenu());
            pst.setInt(3, t.getBlog_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
	}


	public void supprimer(Commentaire t) {
		  try {
            String requete = "Update commentaire set contenu = 'supprimé' WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,t.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
	}

	public List<Commentaire> afficher() {
		 List <Commentaire> commentaires = new ArrayList();

        try {
            String requete = "SELECT * FROM commentaire ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                commentaires.add(new Commentaire(rs.getString("author_id"),rs.getDate("date"),rs.getString("contenu"),rs.getInt("blog_client_id")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return commentaires;
	}
        
        public List<Commentaire> getByIdBlog(int id) {
		 List <Commentaire> commentaires = new ArrayList();

        try {
            String requete = "SELECT * FROM commentaire where blog_client_id ="+id;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                commentaires.add(new Commentaire(rs.getString("author_id"),rs.getDate("date"),rs.getString("contenu"),rs.getInt("blog_client_id")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return commentaires;
	}
}
