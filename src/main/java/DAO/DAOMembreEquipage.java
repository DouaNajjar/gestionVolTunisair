package DAO;

import Classes.Employe;
import Classes.MembreEquipage;
import Classes.Pilote;

import java.sql.*;
import java.util.ArrayList;

public class DAOMembreEquipage {
    private static Connection cn = Connexion.seConnecter();

    public static ArrayList<Employe> lister() {
        ArrayList<Employe> listeMembreEquipage = new ArrayList<>();

        String requete = "select * from membre_equipage;";
        int id_membre_equipage;
        String nom, prenom, email, cin, tel;
        Date date_embauche;
        boolean disponibilite;

        Employe me;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                id_membre_equipage = rs.getInt(1);
                nom = rs.getString(2);
                prenom = rs.getString(3);
                tel = rs.getString(4);
                email = rs.getString(5);
                date_embauche = rs.getDate(6);
                disponibilite = rs.getBoolean(7);
                cin = rs.getString(8);
                me = new MembreEquipage(cin, nom, prenom, email, date_embauche, disponibilite, tel);
                me.setId(id_membre_equipage);
                listeMembreEquipage.add(me);


            }
            System.out.println("consultation ok membre equipage");
        } catch (SQLException e) {
            System.out.println("probleme de consultation de membre equipage");
        }
        return listeMembreEquipage;
    }

    public static boolean ajouter(MembreEquipage me) {

        String requete = "insert into membre_equipage values(null,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, me.getNom());
            pst.setString(2, me.getPrenom());
            pst.setString(3, me.getNum_tel());
            pst.setString(4, me.getEmail());
            pst.setDate(5, me.getDateEmbauche());
            pst.setBoolean(6, me.getDisponibilite());
            pst.setString(7, me.getCin());


            int n = pst.executeUpdate();
            if (n >= 1) {
                System.out.println("ajout réussi de membre equipage");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("problème de requête : " + ex.getMessage());
        }
        return false;
    }

    public static ArrayList<Employe> chercherMembres(String recherche) {
        ArrayList<Employe> listeMembres = new ArrayList<>();

        String requete = "SELECT * FROM membre_equipage WHERE " + "nom LIKE '%" + recherche + "%' OR " + "prenom LIKE '%" + recherche + "%' OR " + "num_tel LIKE '%" + recherche + "%' OR " + "email LIKE '%" + recherche + "%' OR " + "cin LIKE '%" + recherche + "%'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Employe me = new MembreEquipage();
                me.setId(rs.getInt("id_membre_equipage"));
                me.setNom(rs.getString("nom"));
                me.setPrenom(rs.getString("prenom"));
                me.setNum_tel(rs.getString("num_tel"));
                me.setEmail(rs.getString("email"));
                me.setDateEmbauche(rs.getDate("date_embauche"));
                me.setDisponibilite(rs.getBoolean("disponibilite"));
                me.setCin(rs.getString("cin"));


                listeMembres.add(me);
            }
            System.out.println("Recherche membre réussie");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche membre");
        }

        return listeMembres;
    }

    public static boolean supprimer(MembreEquipage me) {

        String requete = "delete from membre_equipage where id_membre_equipage = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, me.getId());

            int n = pst.executeUpdate();
            if (n >= 1) {
                System.out.println("Suppression réussie de membre");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Problème de requête de suppression : " + ex.getMessage());
        }
        return false;
    }
    public static boolean modifier(MembreEquipage me) {
        try {
            String sql = "UPDATE membre_equipage SET nom = ?, prenom = ?, email = ?, date_embauche = ?, disponibilite = ?, num_tel = ?,cin=? WHERE id_membre_equipage = ?";

            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, me.getNom());
            pstmt.setString(2, me.getPrenom());
            pstmt.setString(3, me.getEmail());
            pstmt.setDate(4, me.getDateEmbauche());
            pstmt.setBoolean(5, me.getDisponibilite());
            pstmt.setString(6, me.getNum_tel());
            pstmt.setString(7, me.getCin());
            pstmt.setInt(8,me.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
