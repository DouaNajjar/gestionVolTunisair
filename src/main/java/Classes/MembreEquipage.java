package Classes;

import java.sql.Date;

public class MembreEquipage implements Comparable<MembreEquipage> {
    private int idMembreEquipage;
    private String nom;
    private String prenom;
    private String email;

    public MembreEquipage() {
    }

    @Override
    public String toString() {
        return "MembreEquipage{" +
                "idMembreEquipage=" + idMembreEquipage +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", dateEmbauche=" + dateEmbauche +
                ", disponibilite=" + disponibilite +
                ", cin='" + cin + '\'' +
                '}';
    }

    public MembreEquipage(String cin, String nom, String prenom,  String email, Date dateEmbauche, Boolean disponibilite) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateEmbauche = dateEmbauche;
        this.disponibilite = disponibilite;

    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public int getIdMembreEquipage() {
        return idMembreEquipage;
    }

    public void setIdMembreEquipage(int idMembreEquipage) {
        this.idMembreEquipage = idMembreEquipage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    private Date dateEmbauche;
    private Boolean disponibilite;
    private String cin;

    @Override
    public int compareTo(MembreEquipage me) {
        return this.cin.compareTo(me.cin);
    }
}
