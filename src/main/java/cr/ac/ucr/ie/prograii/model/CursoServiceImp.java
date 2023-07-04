package cr.ac.ucr.ie.prograii.model;

import cr.ac.ucr.ie.prograii.service.EditorialDAO;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CursoServiceImp implements EditorialService {
    EditorialDAO editorialDAO;
    @Override
    public List<Editorial> listar() throws JDOMException, IOException {
        editorialDAO = EditorialDAO.abrirDocumento("editoriales.xml");
        ArrayList<Editorial> editoriales = editorialDAO.getEditoriales();

        return editoriales;
    }
}
