package DAO;

import Classes.Avion;


import java.sql.*;
import java.util.ArrayList;

public class DAOAvion {
    public static ArrayList<Avion> lister() {
        ArrayList<Avion> listeAvions = new ArrayList<>();
        Connection cn = Connexion.seConnecter();
        String requete = "SELECT * FROM avion;";
        int id_avion, capacite;
        String matricule, marque, modele;
        boolean disponible;
        Avion a;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                id_avion = rs.getInt("id_avion");
                matricule = rs.getString("matricule");
                marque = rs.getString("marque");
                modele = rs.getString("modele");
                capacite = rs.getInt("capacite");
                disponible = rs.getBoolean("disponible");

                a = new Avion(matricule, marque, modele, capacite, disponible);
                a.setId_avion(id_avion);

                listeAvions.add(a);
            }
            System.out.println("Consultation avion OK");
        } catch (SQLException e) {
            System.out.println("Problème de consultation avion");
        }
        return listeAvions;
    }
        public static boolean ajouter(Avion a) {
            Connection cn = Connexion.seConnecter();
            String requete = "insert into avion values(null,?,?,?,?,?)";

            try {
                PreparedStatement pst = cn.prepareStatement(requete);
                pst.setString(1, a.getMatricule());
                pst.setString(2, a.getMarque());
                pst.setString(3, a.getModele());
                pst.setInt(4,a.getCapacite());
                pst.setBoolean(5,a.isDisponible());

                int n = pst.executeUpdate();
                if (n >= 1) {
                    System.out.println("ajout réussi d'avion");
                    return true;
                }
            } catch (SQLException ex) {
                System.out.println("problème de requête : " + ex.getMessage());
            }
            return false;
        }
        public static boolean supprimer(Avion a) {
            Connection cn = Connexion.seConnecter();
            String requete = "delete from avion where matricule = ?";

            try {
                PreparedStatement pst = cn.prepareStatement(requete);
                pst.setString(1, a.getMatricule());

                int n = pst.executeUpdate();
                if (n >= 1) {
                    System.out.println("Suppression réussie d'avion");
                    return true;
                }
            } catch (SQLException ex) {
                System.out.println("Problème de requête de suppression : " + ex.getMessage());
            }
            return false;
        }
    public static ArrayList<Avion> chercherAvion(String recherche) {
        ArrayList<Avion> listeAvions = new ArrayList<>();
        Connection cn = Connexion.seConnecter();
        String requete = "SELECT * FROM avion WHERE "
                + "matricule LIKE '%" + recherche + "%' OR "
                + "marque LIKE '%" + recherche + "%' OR "
                + "modele LIKE '%" + recherche + "%'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Avion a = new Avion();
                a.setId_avion(rs.getInt("id_avion"));
                a.setMatricule(rs.getString("matricule"));
                a.setMarque(rs.getString("marque"));
                a.setModele(rs.getString("modele"));
                a.setCapacite(rs.getInt("capacite"));
                a.setDisponible(rs.getBoolean("disponible"));

                listeAvions.add(a);
            }
            System.out.println("Recherche avion réussie");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche avion");
        }

        return listeAvions;
    }
    public static boolean modifier(Avion a) {
        Connection cn = Connexion.seConnecter();
        String requete = "UPDATE avion SET matricule = ?, marque = ?, modele = ?, capacite = ?, disponible = ? WHERE id_avion = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, a.getMatricule());
            pst.setString(2, a.getMarque());
            pst.setString(3, a.getModele());
            pst.setInt(4, a.getCapacite());
            pst.setBoolean(5, a.isDisponible());
            pst.setInt(6, a.getId_avion());

            int n = pst.executeUpdate();
            if (n >= 1) {
                System.out.println("Modification réussie d'avion");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Problème de requête de modification : " + ex.getMessage());
        }
        return false;
    }
    }
