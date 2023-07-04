package cr.ac.ucr.ie.prograii.model;

import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.List;

public interface EditorialService {
    List<Editorial> listar() throws JDOMException, IOException;
}
