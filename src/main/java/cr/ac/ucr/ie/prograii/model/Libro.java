package cr.ac.ucr.ie.prograii.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Libro {
    private int idLibro;
    private String titulo;
    private String isbn;
    private List<Autor> autores;
    private Editorial editorial;
    private Tematica tematica;

    public Libro() {
        this.autores = new ArrayList<>();
        this.editorial = new Editorial();
        this.tematica = new Tematica();
        this.isbn = this.titulo = "";
    }

    public Libro(int idLibro, String titulo, String isbn, List<Autor> autores, Editorial editorial, Tematica tematica) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autores = autores;
        this.editorial = editorial;
        this.tematica = tematica;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }

}
