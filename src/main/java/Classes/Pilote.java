package Classes;

import java.sql.Date;

public class Pilote {
    private int idPilote;
    private String nom;
    private String prenom;
    private String email;

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    @Override
    public String toString() {
        return "Pilote{" +
                "idPilote=" + idPilote +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", dateEmbauche=" + dateEmbauche +
                ", disponibilite=" + disponibilite +
                ", cin='" + cin + '\'' +
                '}';
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Pilote() {
    }

    public Pilote( String cin, String nom, String prenom, String email, Date dateEmbauche, Boolean disponibilite) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateEmbauche = dateEmbauche;
        this.disponibilite = disponibilite;

    }

    public int getIdPilote() {
        return idPilote;
    }

    public void setIdPilote(int idPilote) {
        this.idPilote = idPilote;
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
}
