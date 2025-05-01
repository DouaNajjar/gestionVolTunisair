package Classes;

import java.time.LocalDateTime;
import java.util.TreeSet;

public class Vol {
    private int id_vol;
    private String numero_vol;
    private LocalDateTime  date_heure_depart;
    private LocalDateTime date_heure_arrivee;
    private String destination;
    private String provenance;
    private TypeTrajet type_trajet;
    private int nombre_passagers;

    public Vol() {
    }

    public Vol(String numero_vol, LocalDateTime date_heure_depart, LocalDateTime date_heure_arrivee, String destination, TypeTrajet type_trajet, String provenance, int nombre_passagers, Avion avion, Pilote pilote) {
        this.numero_vol = numero_vol;
        this.date_heure_depart = date_heure_depart;
        this.date_heure_arrivee = date_heure_arrivee;
        this.destination = destination;
        this.type_trajet = type_trajet;
        this.provenance = provenance;
        this.nombre_passagers = nombre_passagers;
        this.avion = avion;
        this.pilote = pilote;
        this.setMembreEquipage=new TreeSet<MembreEquipage>();
    }

    @Override
    public String toString() {
        return "Vol{" +
                "id_vol=" + id_vol +
                ", numero_vol='" + numero_vol + '\'' +
                ", date_heure_depart=" + date_heure_depart +
                ", date_heure_arrivee=" + date_heure_arrivee +
                ", destination='" + destination + '\'' +
                ", provenance='" + provenance + '\'' +
                ", type_trajet=" + type_trajet +
                ", nombre_passagers=" + nombre_passagers +
                ", avion=" + avion +
                ", pilote=" + pilote +
                '}';
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Pilote getPilote() {
        return pilote;
    }

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    public int getId_vol() {
        return id_vol;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    public String getNumero_vol() {
        return numero_vol;
    }


    public void setNumero_vol(String numero_vol) {
        this.numero_vol = numero_vol;
    }

    public LocalDateTime getDate_heure_depart() {
        return date_heure_depart;
    }

    public void setDate_heure_depart(LocalDateTime date_heure_depart) {
        this.date_heure_depart = date_heure_depart;
    }

    public LocalDateTime getDate_heure_arrivee() {
        return date_heure_arrivee;
    }

    public void setDate_heure_arrivee(LocalDateTime date_heure_arrivee) {
        this.date_heure_arrivee = date_heure_arrivee;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public TypeTrajet getType_trajet() {
        return type_trajet;
    }

    public void setType_trajet(TypeTrajet type_trajet) {
        this.type_trajet = type_trajet;
    }

    public int getNombre_passagers() {
        return nombre_passagers;
    }


    public void setNombre_passagers(int nombre_passagers) {
        this.nombre_passagers = nombre_passagers;
    }



    private Avion avion;
    private Pilote pilote;
    private TreeSet<MembreEquipage>setMembreEquipage;

}
