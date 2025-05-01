package DAO;

import Classes.Employe;
import Classes.Pilote;

import java.sql.*;
import java.util.ArrayList;

public class DAOPilote {
    private static Connection cn = Connexion.seConnecter();

    public static ArrayList<Employe> lister() {
        ArrayList<Employe> listePilotes = new ArrayList<>();

        String requete = "select * from pilote;";
        int id_pilote;
        String nom, prenom, email, cin, tel;
        Date date_embauche;
        boolean disponibilite;

        Employe p;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                id_pilote = rs.getInt(1);
                nom = rs.getString(2);
                prenom = rs.getString(3);
                tel = rs.getString(4);
                email = rs.getString(5);
                date_embauche = rs.getDate(6);
                disponibilite = rs.getBoolean(7);
                cin = rs.getString(8);
                p = new Pilote(cin, nom, prenom, email, date_embauche, disponibilite, tel);
                p.setId(id_pilote);
                listePilotes.add(p);


            }
            System.out.println("consultation ok pilote");
        } catch (SQLException e) {
            System.out.println("probleme de consultation de pilote");
        }
        return listePilotes;
    }

    public static boolean ajouter(Pilote p) {

        String requete = "insert into pilote values(null,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.setString(3, p.getNum_tel());
            pst.setString(4, p.getEmail());
            pst.setDate(5, p.getDateEmbauche());
            pst.setBoolean(6, p.getDisponibilite());
            pst.setString(7, p.getCin());


            int n = pst.executeUpdate();
            if (n >= 1) {
                System.out.println("ajout réussi de pilote");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("problème de requête : " + ex.getMessage());
        }
        return false;
    }

    public static ArrayList<Employe> chercherPilotes(String recherche) {
        ArrayList<Employe> listePilotes = new ArrayList<>();

        String requete = "SELECT * FROM pilote WHERE " + "nom LIKE '%" + recherche + "%' OR " + "prenom LIKE '%" + recherche + "%' OR " + "num_tel LIKE '%" + recherche + "%' OR " + "email LIKE '%" + recherche + "%' OR " + "cin LIKE '%" + recherche + "%'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Employe p = new Pilote();
                p.setId(rs.getInt("id_pilote"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setNum_tel(rs.getString("num_tel"));
                p.setEmail(rs.getString("email"));
                p.setDateEmbauche(rs.getDate("date_embauche"));
                p.setDisponibilite(rs.getBoolean("disponibilite"));
                p.setCin(rs.getString("cin"));


                listePilotes.add(p);
            }
            System.out.println("Recherche pilote réussie");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche pilote");
        }

        return listePilotes;
    }

    public static boolean supprimer(Pilote p) {

        String requete = "delete from pilote where id_pilote = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, p.getId());

            int n = pst.executeUpdate();
            if (n >= 1) {
                System.out.println("Suppression réussie de pilote");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Problème de requête de suppression : " + ex.getMessage());
        }
        return false;
    }

    public static boolean modifier(Pilote p) {
        try {
            String sql = "UPDATE pilote SET nom = ?, prenom = ?, email = ?, date_embauche = ?, disponibilite = ?, num_tel = ?,cin=? WHERE id_pilote = ?";

            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, p.getNom());
            pstmt.setString(2, p.getPrenom());
            pstmt.setString(3, p.getEmail());
            pstmt.setDate(4, p.getDateEmbauche());
            pstmt.setBoolean(5, p.getDisponibilite());
            pstmt.setString(6, p.getNum_tel());
            pstmt.setString(7, p.getCin());
            pstmt.setInt(8,p.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
