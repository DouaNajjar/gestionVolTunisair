package DAO;

import Classes.MembreEquipage;
import java.sql.*;
import java.util.ArrayList;

public class DAOMembreEquipage {
    public static ArrayList<MembreEquipage> lister(){
        ArrayList<MembreEquipage> listeMembreEquipage=new ArrayList<>();
        Connection cn=Connexion.seConnecter();
        String requete="select * from membre_equipage;";
        int  id_membre_equipage;
        String nom,prenom,email,cin;
        Date date_embauche;
        boolean disponibilite;

        MembreEquipage me;
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                id_membre_equipage=rs.getInt(1);
                nom=rs.getString(2);
                prenom=rs.getString(3);
                email=rs.getString(4);
                date_embauche=rs.getDate(5);
                disponibilite=rs.getBoolean(6);
                cin=rs.getString(7);
                me=new MembreEquipage(cin,nom,prenom,email,date_embauche,disponibilite);
                me.setIdMembreEquipage(id_membre_equipage);
                listeMembreEquipage.add(me);


            }
            System.out.println("consultation ok membre equipage");
        }catch(SQLException e){
            System.out.println("probleme de consultation de membre equipage");
        }
       return listeMembreEquipage;
    }
    public static boolean ajouter(MembreEquipage me) {
        Connection cn = Connexion.seConnecter();
        String requete = "insert into membre_equipage values(null,?,?,?,?,?,?)";

        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1,me.getNom());
            pst.setString(2, me.getPrenom());
            pst.setString(3,me.getEmail());
            pst.setDate(4,me.getDateEmbauche());
            pst.setBoolean(5,me.getDisponibilite());
            pst.setString(6, me.getCin());



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
    public static boolean supprimer(MembreEquipage me) {
        Connection cn = Connexion.seConnecter();
        String requete = "delete from membre_equipage where cin = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1,me.getCin());

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
    public static MembreEquipage chercherParCIN(String cin){
        Connection cn=Connexion.seConnecter();
        String requete="select * from membre_equipage where cin='"+cin+"'";
        String nom,prenom,email;
        Date date_embauche;
        boolean disponibilite;
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                cin = rs.getString("cin");
                nom = rs.getString("nom");
                prenom = rs.getString("prenom");
                email = rs.getString("email");
                date_embauche=rs.getDate("date_embauche");
                disponibilite=rs.getBoolean("disponibilite");
                MembreEquipage membre = new MembreEquipage(cin, nom, prenom, email, date_embauche, disponibilite);
                return membre;
            }}catch(SQLException e){
            System.out.println("erreur"+e.getMessage());
        }
        return null;
    }
    public static MembreEquipage chercherParNomOuPrenom(String nomRecherche, String prenomRecherche) {
        Connection cn = Connexion.seConnecter();
        String requete = "SELECT * FROM membre_equipage WHERE nom = '" + nomRecherche + "' OR prenom = '" + prenomRecherche + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String cin = rs.getString("cin");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                Date date_embauche = rs.getDate("date_embauche");
                boolean disponibilite = rs.getBoolean("disponibilite");
                MembreEquipage membre = new MembreEquipage(cin, nom, prenom, email, date_embauche, disponibilite);
                return membre;
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return null;
    }

}
