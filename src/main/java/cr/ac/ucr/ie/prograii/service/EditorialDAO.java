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
        return new EditorialDAO("editoriales",rutaDocumento);
    }

    private EditorialDAO(String rutaDocumento) throws IOException, JDOMException {
        File file = new File(rutaDocumento);

        if (!file.exists()) {
            //se encarga de crear tanto el DOM y como Documento XML
            this.rutaDocumento = rutaDocumento;
            this.raiz = new Element("editoriales");
            this.document = new Document(raiz);
            guardar();
        }else{
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);
            this.document = saxBuilder.build(rutaDocumento);
            this.raiz = document.getRootElement();
            this.rutaDocumento = rutaDocumento;

        }
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

    // revisa el ultimo editorial y saca el id, y le suma 1
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

    // eliminar
    public void eliminarEditorial(int codEditorial) throws IOException {
        List<Element> editoriales = raiz.getChildren("editorial");

        for (Element editorial : editoriales) {
            int id = Integer.parseInt(editorial.getAttributeValue("id"));
            if (id == codEditorial) {
                raiz.removeContent(editorial);
                break;
            }
        }
        guardar();
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

    // busca por id una editorial
    public boolean buscar(int idEditorial) throws DataConversionException {
        List<Element> eListaEditoriales = raiz.getChildren();
        for (Element eEditorial : eListaEditoriales) {
            int codEditorialAcutal = eEditorial.getAttribute("id").getIntValue();
            if (codEditorialAcutal == idEditorial) return true;
        }
        return false;
    }

    // busca por nombre una editorial
    public boolean buscarIguales(String nombreEditorial, String ciudadEditorial) {
        Element editorial = raiz.getChild("editorial");
        if (editorial != null) {
            String nombre = editorial.getAttributeValue("nombre");
            String ciudad = editorial.getAttributeValue("ciudad");
            return nombreEditorial.equals(nombre) && ciudadEditorial.equals(ciudad);
        }
        return false;
    }

    // editar
    public void editarEditorial(int editCode, Editorial editorialActualizado) throws IOException {
        List<Element> editoriales = raiz.getChildren("editorial");

        for (Element editorial : editoriales) {
            int id = Integer.parseInt(editorial.getAttributeValue("id"));
            if (id == editCode) {
                editorial.getChild("nombre").setText(editorialActualizado.getNombreEditorial());
                editorial.getChild("ciudad").setText(editorialActualizado.getCiudad());
                break;
            }
        }
        guardar();
    }
}

