package cr.ac.ucr.ie.prograii.service;

import cr.ac.ucr.ie.prograii.model.Editorial;
import cr.ac.ucr.ie.prograii.model.Libro;
import cr.ac.ucr.ie.prograii.model.Tematica;
import org.jdom2.JDOMException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class EditorialDAOTest {
    @Test
    void crear_documento_funciona(){
        try {
            EditorialDAO editorialDAO = EditorialDAO.crearDocumento("C:\\Users\\gabri\\Desktop\\Cosas de progra\\editoriales.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void abrir_documento_funciona() {
        try {
            EditorialDAO editorialDAO = EditorialDAO.abrirDocumento("editoriales.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void guardarEditorial_fuciona(){
            List<Libro> libros = new ArrayList<Libro>();


            // Primer libro
            Libro libro1 = new Libro();
            libro1.setIdLibro(1);
            libro1.setTitulo("El señor de los anillos");
            libro1.setIsbn("9788445004954");
            libro1.setAutores(new ArrayList<>()); // Añade autores a la lista de autores
            libro1.getItems(); // Añade items a la lista de items
            libro1.setEditorial(new Editorial()); // Asigna una editorial
            libro1.setTematica(new Tematica()); // Asigna una temática
            libros.add(libro1);

        Editorial editorial1 = new Editorial(01, "Monge","Jalisco",libros);
        Editorial editorial2 = new Editorial(02, "Casa Azul","New York",libros);

        try {
            EditorialDAO editorialDAO = EditorialDAO.abrirDocumento("C:\\Users\\gabri\\Desktop\\Cosas de progra\\editoriales.xml");
            editorialDAO.insertarEditorial(editorial1);
            editorialDAO.insertarEditorial(editorial2);
        } catch (IOException | JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getTEditorial_funciona(){
        try {
            EditorialDAO editorialDAO = EditorialDAO.abrirDocumento("editoriales.xml");
            List<Editorial> editorials = editorialDAO.getEditoriales();
            System.out.println(editorials.toString());
        } catch (IOException | JDOMException e) {
            throw new RuntimeException(e);
        }
    }
  
}