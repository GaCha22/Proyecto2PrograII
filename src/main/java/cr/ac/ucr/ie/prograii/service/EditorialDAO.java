package cr.ac.ucr.ie.prograii.service;

import cr.ac.ucr.ie.prograii.model.Editorial;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditorialDAO {
    private Document document;
    private Element raiz;
    private String rutaDocumento;

    public EditorialDAO(String nombreRaiz, String rutaDocumento) {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
    }

    // crea un documento
    public static EditorialDAO crearDocumento(String rutaDocumento){
        return new EditorialDAO(rutaDocumento, "editoriales");
    }

    private EditorialDAO(String rutaDocumento) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);

        this.document = saxBuilder.build(rutaDocumento);
        this.raiz = document.getRootElement();
        this.rutaDocumento = rutaDocumento;
    }

    public static EditorialDAO abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        return new EditorialDAO(rutaDocumento);
    }

    // guardar
    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document, new FileWriter(this.rutaDocumento));

        // extra para revisar mejor
        xmlOutputter.output(this.document, System.out);
    }

    // insertar Editorial
    public void insertarEditorial(Editorial editorial) throws IOException {
        Element eEditorial = new Element("editorial");
        eEditorial.setAttribute("id", String.valueOf(editorial.getIdEditorial()));

        // agregar elemento nombre
        Element eNombre = new Element("nombre");
        eNombre.addContent(editorial.getNombreEditorial());
        eEditorial.addContent(eNombre);

        // agregar elemento ciudad
        Element eCiudad = new Element("ciudad");
        eCiudad.addContent(editorial.getCiudad());
        eEditorial.addContent(eCiudad);

        raiz.addContent(eEditorial);
        guardar();
    }

    // get de editoriales
    public ArrayList<Editorial> getEditoriales() throws DataConversionException {
        List eListaEditoriales = raiz.getChildren();

        // castear la lista
        ArrayList<Editorial> editoriales = new ArrayList<Editorial>();

        for (Object obj:eListaEditoriales) {
            Element eEditorial = (Element) obj;

            Editorial editorialActual = new Editorial();
            editorialActual.setIdEditorial(eEditorial.getAttribute("id").getIntValue());
            editorialActual.setNombreEditorial(eEditorial.getChildText("nombre"));
            editorialActual.setCiudad(eEditorial.getChildText("ciudad"));
            editoriales.add(editorialActual);
        }
        return editoriales;
    }

}









