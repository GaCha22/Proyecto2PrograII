package cr.ac.ucr.ie.prograii.service;

import cr.ac.ucr.ie.prograii.model.Editorial;
import org.jdom2.JDOMException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class EditorialDAOTest {
    @Test
    void crear_documento_funciona(){
        EditorialDAO editorialDAO = EditorialDAO.crearDocumento("editoriales.xml");
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
    void guardarTematica_fuciona(){
        Editorial editorial1 = new Editorial(01, "Monge", "Jalisco",14,);

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