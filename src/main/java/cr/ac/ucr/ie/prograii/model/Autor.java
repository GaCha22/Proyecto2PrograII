package cr.ac.ucr.ie.prograii.model;

import java.util.ArrayList;
import java.util.List;

public class Autor {
    private int idAutor;
    private String nombre;
    private String apellidosAutor;
    private List<Libro> librosEscritos;

    public Autor() {
        this.nombre = this.apellidosAutor = "";
        this.librosEscritos = new ArrayList<>();
    }

    public Autor(int idAutor, String nombre, String apellidosAutor, List<Libro> librosEscritos) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.apellidosAutor = apellidosAutor;
        this.librosEscritos = librosEscritos;
    }

    public Autor(int idAutor, String nombre, String apellidosAutor) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.apellidosAutor = apellidosAutor;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidosAutor() {
        return apellidosAutor;
    }

    public void setApellidosAutor(String apellidosAutor) {
        this.apellidosAutor = apellidosAutor;
    }

    public List<Libro> getLibrosEscritos() {
        return librosEscritos;
    }

    public void setLibrosEscritos(List<Libro> librosEscritos) {
        this.librosEscritos = librosEscritos;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "idAutor=" + idAutor +
                ", nombre='" + nombre + '\'' +
                ", apellidosAutor='" + apellidosAutor + '\'' +
                '}';
    }
}
