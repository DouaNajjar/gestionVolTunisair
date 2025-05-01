package DAO;

import Classes.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUser {
    public static User authenticate(String email, String password) {
        Connection cn = Connexion.seConnecter();
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password); // En production, utilisez plutôt un hash

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'authentification: " + e.getMessage());
        }
        return null;
    }
}