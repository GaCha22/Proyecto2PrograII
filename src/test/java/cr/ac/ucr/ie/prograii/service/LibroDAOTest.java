package cr.ac.ucr.ie.prograii.service;


import cr.ac.ucr.ie.prograii.model.Autor;
import cr.ac.ucr.ie.prograii.model.Editorial;
import cr.ac.ucr.ie.prograii.model.Libro;
import cr.ac.ucr.ie.prograii.model.Tematica;
import org.jdom2.JDOMException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class LibroDAOTest  {

   private LibroDAO libroDAO;

    @Test
    public void abrir_documento_funciona(){
        try {
            libroDAO.abrirDocumento("libros.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }
@Test
    public void guardarLibro_funciona() {

        Libro libro1 = new Libro();
        Libro libro2 = new Libro();
        libro1.setIdLibro(2);
        libro1.setTitulo("Caperucita Roja");
        libro1.setIsbn("ss12");
        libro1.setTematica(new Tematica(12,"tematica1"));
        Editorial editorial = new Editorial();
        editorial.setNombreEditorial("editorial1");
        libro1.setEditorial(editorial);

        Autor autor1 = new Autor();
        autor1.setNombre("Gabriel");
        autor1.setIdAutor(1);
        autor1.setApellidosAutor("chaves");

        List<Autor> autores = new ArrayList<>();
        autores.add(autor1);

        libro1.setAutores(autores);

        try {
            libroDAO = LibroDAO.abrirDocumento("C:\\Users\\gabri\\Desktop\\Cosas de progra\\libros.xml");
            libroDAO.insertarLibro(libro1);
            libroDAO.insertarLibro(libro1);
            libroDAO.guardar();
        } catch (IOException | JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getLibro_funciona() {
        LibroDAO libroDAO;

        try {
            libroDAO = LibroDAO.abrirDocumento("libros.xml");
            ArrayList<Libro> libros  = libroDAO.getLibros();
            System.out.println("Impresión libros:");
            for (Iterator<Libro> libroActual = libros.iterator(); libroActual.hasNext();) {
                Libro libro = libroActual.next();
                System.out.println(libro.getIdLibro() + " " + libro.getTitulo() + " " + libro.getTematica() + " " + libro.getEditorial());
            }

        } catch (IOException | JDOMException e) {
            throw new RuntimeException(e);
        }
    }

}