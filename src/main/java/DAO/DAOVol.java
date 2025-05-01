package DAO;

import Classes.Vol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DAOVol {
    public static boolean ajouter(Vol v) {
        Connection cn = Connexion.seConnecter();
        String requete = "INSERT INTO vol VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, v.getNumero_vol());
            pst.setTimestamp(2, Timestamp.valueOf(v.getDate_heure_depart()));
            pst.setTimestamp(3, Timestamp.valueOf(v.getDate_heure_arrivee()));
            pst.setString(4, v.getDestination());
            pst.setString(5, v.getProvenance());
            pst.setString(6, v.getType_trajet().toString());
            pst.setInt(7, v.getNombre_passagers());
            pst.setInt(8, v.getAvion().getId_avion());

            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Vol ajouté avec succès");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du vol: " + e.getMessage());
        }
        return false;
    }
    public static boolean supprimer(String numero_vol) {
        Connection cn = Connexion.seConnecter();
        String requete = "DELETE FROM vol WHERE numero_vol = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, numero_vol);

            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Vol supprimé avec succès");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du vol: " + e.getMessage());
        }
        return false;
    }
}
