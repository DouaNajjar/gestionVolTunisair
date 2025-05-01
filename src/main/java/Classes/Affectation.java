package Classes;

public class Affectation {
    private Avion avion;

    public MembreEquipage getMembreEquipage() {
        return membreEquipage;
    }

    public Affectation(Avion avion, MembreEquipage membreEquipage) {
        this.avion = avion;
        this.membreEquipage = membreEquipage;
    }

    public void setMembreEquipage(MembreEquipage membreEquipage) {
        this.membreEquipage = membreEquipage;
    }

    @Override
    public String toString() {
        return "Affectation{" +
                "avion=" + avion +
                ", membreEquipage=" + membreEquipage +
                '}';
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    private MembreEquipage membreEquipage;
}
