package DAO;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static Connection con;
    private static String user="root";
    private static String passWord="";

    public static Connection seConnecter() {
        if (con == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gestion_vol", user, passWord);
                System.out.println("connexion établie");
            } catch (ClassNotFoundException e) {
                System.out.println("driver non trouvé" + e.getMessage());
            } catch (SQLException ex) {
                System.out.println("bd non trouvé ou problème d'identification " + ex.getMessage());
            }
        }
        return con;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
