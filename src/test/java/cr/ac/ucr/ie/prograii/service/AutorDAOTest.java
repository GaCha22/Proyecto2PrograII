package cr.ac.ucr.ie.prograii.service;

import cr.ac.ucr.ie.prograii.model.Autor;
import org.jdom2.JDOMException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class AutorDAOTest {

    @Test
    void crear_documento_funciona(){
        try {
            AutorDAO autorDAO = AutorDAO.crearDocumento("autores.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void abrir_documento_funciona(){
        try {
            AutorDAO autorDAO = AutorDAO.abrirDocumento("autores.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void guardarAutor_fuciona(){

        Autor autor1 = new Autor();
        Autor autor2 = new Autor();
        autor1.setIdAutor(1);
        autor2.setIdAutor(2);
        autor1.setNombre("ASADV");
        autor1.setApellidosAutor("ASFDASD");
        autor2.setNombre("ASDFSTF");
        autor2.setApellidosAutor("ASFDALJHGFSD");

        try {
            AutorDAO autorDAO = AutorDAO.abrirDocumento("autores.xml");
            autorDAO.insertarAutor(autor1);
            autorDAO.insertarAutor(autor2);
        } catch (IOException | JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAutores_funciona(){
        try {
            AutorDAO autorDAO = AutorDAO.abrirDocumento("autores.xml");
            List<Autor> autores = autorDAO.getAutores();
            System.out.println(autores.toString());
        } catch (IOException | JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void eliminar_funciona(){
        try {
            AutorDAO autorDAO = AutorDAO.abrirDocumento("autores.xml");
            autorDAO.eliminarAutor(2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

}