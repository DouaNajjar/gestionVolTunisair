package DAO;

import Classes.Pilote;


import java.sql.*;
import java.util.ArrayList;

public class DAOPilote {
    public static ArrayList<Pilote>lister(){
        ArrayList<Pilote> listePilote=new ArrayList<>();
        Connection cn=Connexion.seConnecter();
        String requete="select * from pilote;";
        int  id_pilote;
        String nom,prenom,email,cin;
        Date date_embauche;
        boolean disponibilite;

        Pilote p;
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                id_pilote=rs.getInt(1);
                nom=rs.getString(2);
                prenom=rs.getString(3);
                email=rs.getString(4);
                date_embauche=rs.getDate(5);
                disponibilite=rs.getBoolean(6);
                cin=rs.getString(7);
                p=new Pilote(cin,nom,prenom,email,date_embauche,disponibilite);
                p.setIdPilote(id_pilote);
                listePilote.add(p);


            }
            System.out.println("consultation pilote ok");
        }catch(SQLException e){
            System.out.println("probleme de consultation pilote");
        }
        return listePilote;
    }
    public static boolean ajouter(Pilote p) {
        Connection cn = Connexion.seConnecter();
        String requete = "insert into pilote values(null,?,?,?,?,?,?)";

        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.setString(3, p.getEmail());
            pst.setDate(4,p.getDateEmbauche());
            pst.setBoolean(5,p.getDisponibilite());
            pst.setString(6, p.getCin());



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
    public static boolean supprimer(Pilote p) {
        Connection cn = Connexion.seConnecter();
        String requete = "delete from pilote where cin = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, p.getCin());

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
    public static Pilote chercherParCIN(String cin){
        Connection cn=Connexion.seConnecter();
        String requete="select * from pilote where cin='"+cin+"'";
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
                Pilote pilote = new Pilote(cin, nom, prenom, email, date_embauche, disponibilite);
                return pilote;
            }}catch(SQLException e){
            System.out.println("erreur"+e.getMessage());
        }
        return null;
    }
    public static Pilote chercherParNomOuPrenom(String nomRecherche, String prenomRecherche) {
        Connection cn = Connexion.seConnecter();
        String requete = "SELECT * FROM pilote WHERE nom = '" + nomRecherche + "' OR prenom = '" + prenomRecherche + "'";

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
                return new Pilote(cin, nom, prenom, email, date_embauche, disponibilite);
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return null;
    }

}
