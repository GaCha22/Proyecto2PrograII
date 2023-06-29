package cr.ac.ucr.ie.prograii.model;

public class Tematica {
    private int idTipo;
    private String nombreTematica;

    public Tematica() {
        this.nombreTematica = "";
    }

    public Tematica(int idTipo, String nombreTematica) {
        this.idTipo = idTipo;
        this.nombreTematica = nombreTematica;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombreTematica() {
        return nombreTematica;
    }

    public void setNombreTematica(String nombreTematica) {
        this.nombreTematica = nombreTematica;
    }
}
