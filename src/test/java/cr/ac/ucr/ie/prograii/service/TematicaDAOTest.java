package cr.ac.ucr.ie.prograii.service;

import cr.ac.ucr.ie.prograii.model.Tematica;
import org.jdom2.JDOMException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TematicaDAOTest {
    @Test
    void crear_documento_funciona(){
        try {
            TematicaDAO tematicaDAO = TematicaDAO.crearDocumento("tematicas.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void abrir_documento_funciona(){
        try {
            TematicaDAO tematicaDAO = TematicaDAO.abrirDocumento("tematicas.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void guardarTematica_fuciona(){
        Tematica tematica1 = new Tematica(1, "Romance");
        Tematica tematica2 = new Tematica(2, "Terror");
        Tematica tematica3 = new Tematica(3, "Suspenso");
        Tematica tematica4 = new Tematica(4, "Acción");

        try {
            TematicaDAO tematicaDAO = TematicaDAO.abrirDocumento("tematicas.xml");
            tematicaDAO.insertarTematica(tematica1);
            tematicaDAO.insertarTematica(tematica2);
            tematicaDAO.insertarTematica(tematica3);
            tematicaDAO.insertarTematica(tematica4);
        } catch (IOException | JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getTematica_funciona(){
        try {
            TematicaDAO tematicaDAO = TematicaDAO.abrirDocumento("tematicas.xml");
            List<Tematica> tematicas = tematicaDAO.getTematicas();
            System.out.println(tematicas.toString());
        } catch (IOException | JDOMException e) {
            throw new RuntimeException(e);
        }
    }

}