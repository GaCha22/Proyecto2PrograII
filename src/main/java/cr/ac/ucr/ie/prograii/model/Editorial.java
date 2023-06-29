package cr.ac.ucr.ie.prograii.model;

import java.util.ArrayList;
import java.util.List;

public class Editorial {
    private int idEditorial;
    private String nombreEditorial;
    private String ciudad;
    private List<Libro> libros;

    public Editorial() {
        this.ciudad = this.nombreEditorial = "";
        this.libros = new ArrayList<>();
    }

    public Editorial(int idEditorial, String nombreEditorial, String ciudad, List<Libro> libros) {
        this.idEditorial = idEditorial;
        this.nombreEditorial = nombreEditorial;
        this.ciudad = ciudad;
        this.libros = libros;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

}
