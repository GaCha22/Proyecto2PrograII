package cr.ac.ucr.ie.prograii.service;

import cr.ac.ucr.ie.prograii.model.Tematica;
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

public class TematicaDAO {
    private Document document;
    private Element root;
    private String path;

    public TematicaDAO(String rootName, String path) throws IOException {
        this.root = new Element(rootName);
        this.path = path;
        this.document = new Document(root);
        guardar();
    }

    public static TematicaDAO crearDocumento(String documentPath) throws IOException {
        return new TematicaDAO("tematicas", documentPath);
    }

    private TematicaDAO(String documentPath) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);
        this.document = saxBuilder.build(documentPath);
        this.root = document.getRootElement();
        this.path = documentPath;
    }

    public static TematicaDAO abrirDocumento(String documentPath) throws IOException, JDOMException {
        return new TematicaDAO(documentPath);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document,new FileWriter(this.path));
        //Extra
        xmlOutputter.output(this.document,System.out);
    }

    public void insertarTematica(Tematica tematica) throws IOException {
        Element eCurso = new Element("tematica");
        eCurso.setAttribute("idTipo",String.valueOf(tematica.getIdTipo()));

        Element eNombre = new Element("nombre");
        eNombre.addContent(tematica.getNombreTematica());
        eCurso.addContent(eNombre);

        root.addContent(eCurso);
        guardar();
    }

    public ArrayList<Tematica> getTematicas() throws DataConversionException {
        List eListaTematicas = root.getChildren();
        ArrayList<Tematica> tematicas = new ArrayList<>();

        for(Object obj: eListaTematicas) {
            Element eTematica = (Element) obj;
            Tematica tematicaActual = new Tematica();
            tematicaActual.setIdTipo(eTematica.getAttribute("idTipo").getIntValue());
            tematicaActual.setNombreTematica(eTematica.getChildText("nombre"));
            tematicas.add(tematicaActual);
        }
        return tematicas;
    }
}
