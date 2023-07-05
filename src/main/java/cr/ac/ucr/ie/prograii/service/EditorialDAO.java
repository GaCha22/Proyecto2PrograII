package cr.ac.ucr.ie.prograii.service;

import cr.ac.ucr.ie.prograii.model.Editorial;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditorialDAO {
    private Document document;
    private Element raiz;
    private String rutaDocumento;

    public EditorialDAO(String rutaDocumento, String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
        guardar();
    }

    // crea un documento
    public static EditorialDAO crearDocumento(String rutaDocumento) throws IOException {
        return new EditorialDAO(rutaDocumento, "editoriales.xml");
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
        String nuevoId = generarNuevoId();

        Element eEditorial = new Element("editorial");
        eEditorial.setAttribute("id", nuevoId);

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

    public String generarNuevoId() {
        int ultimoId = 0;

        // Obtener la lista de elementos "editorial" del XML
        List<Element> elementosEditorial = raiz.getChildren("editorial");

        // Recorrer la lista y encontrar el último ID
        for (Element elemento : elementosEditorial) {
            String idStr = elemento.getAttributeValue("id");
            int id = Integer.parseInt(idStr);
            if (id > ultimoId) {
                ultimoId = id;
            }
        }

        // Generar el nuevo ID sumándole uno al último ID
        int nuevoId = ultimoId + 1;

        // Retornar el nuevo ID como un String
        return String.valueOf(nuevoId);
    }

    public void eliminarEditorial(String idEditorial) throws IOException {

        List<Element> eListaEditorial = raiz.getChildren();
        int nuevoIdTipo = 1;

        for (int i = 0; i < eListaEditorial.size(); i++) {
            Element eEditorial = eListaEditorial.get(i);
            String actualEditorial = eEditorial.getAttributeValue("id");

            if (actualEditorial.equals(idEditorial)) {
                eListaEditorial.remove(i);
                i--;
                for (Element editorialRestante : eListaEditorial) {
                    editorialRestante.setAttribute("id", String.valueOf(nuevoIdTipo));
                    nuevoIdTipo++;
                }
                guardar();
                return;
            }
        }
    }

    // get de editoriales
    public ArrayList<Editorial> getEditoriales() throws DataConversionException {
        List eListaEditoriales = raiz.getChildren();

        // castear la lista
        ArrayList<Editorial> editoriales = new ArrayList<Editorial>();

        for (Object obj : eListaEditoriales) {
            Element eEditorial = (Element) obj;

            Editorial editorialActual = new Editorial();
            editorialActual.setIdEditorial(eEditorial.getAttribute("id").getIntValue());
            editorialActual.setNombreEditorial(eEditorial.getChildText("nombre"));
            editorialActual.setCiudad(eEditorial.getChildText("ciudad"));
            editoriales.add(editorialActual);
        }
        return editoriales;
    }

    // get de una editorial
    public Editorial getEditorial(int codAreaBuscar) throws DataConversionException {
        List eEditoriales = raiz.getChildren();

        Editorial editorialActual = null;
        for (Object obj : eEditoriales) {
            Element eEditorial = (Element) obj;

            int editorialBuscando = eEditorial.getAttribute("id").getIntValue();
            if (codAreaBuscar == editorialBuscando) {
                editorialActual = new Editorial();

                editorialActual.setIdEditorial(editorialBuscando);
                editorialActual.setNombreEditorial(eEditorial.getChildText("nombre"));
                editorialActual.setCiudad(eEditorial.getChildText("ciudad"));
            }
        }
        return editorialActual;
    }

    public boolean buscar(String idAbuscar) {
        Element editorial = raiz.getChild("editorial");
        if (editorial != null) {
            String id = editorial.getAttributeValue("id");
            return idAbuscar.equals(id);
        }
        return false;
    }

    // borra un elemento del xml
}

