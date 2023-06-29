package cr.ac.ucr.ie.prograii.service;

import cr.ac.ucr.ie.prograii.model.Autor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.ie.prograii.model.Editorial;
import cr.ac.ucr.ie.prograii.model.Libro;
import cr.ac.ucr.ie.prograii.model.LibroItem;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class AutorDAO {
    private Document document;
    private Element root;
    private String path;

    public AutorDAO(String rootName, String path) throws IOException {
        this.root = new Element(rootName);
        this.path = path;
        this.document = new Document(root);
        guardar();
    }

    public static AutorDAO crearDocumento(String documentPath) throws IOException {
        return new AutorDAO(documentPath, "cursos.xml");
    }

    private AutorDAO(String documentPath) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);
        this.document = saxBuilder.build(documentPath);
        this.root = document.getRootElement();
        this.path = documentPath;
    }

    public static AutorDAO abrirDocumento(String documentPath) throws IOException, JDOMException {
        return new AutorDAO(documentPath);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document,new FileWriter(this.path));
        //Extra
        xmlOutputter.output(this.document,System.out);
    }

    public void insertarCurso(Autor autor) throws IOException {
        Element eAutor = new Element("autor");
        eAutor.setAttribute("id",String.valueOf(autor.getIdAutor()));

        Element eNombre = new Element("nombre");
        eNombre.addContent(autor.getNombre());
        eAutor.addContent(eNombre);

        Element eApellidos = new Element("apellidos");
        eApellidos.addContent(autor.getApellidosAutor());
        eAutor.addContent(eApellidos);

        root.addContent(eAutor);
        guardar();
    }

    public ArrayList<Autor> getAutores() throws DataConversionException {
        List eListaAAutores = root.getChildren();
        ArrayList<Autor> autores = new ArrayList<Autor>();

        for(Object obj: eListaAAutores) {
            Element eAutor = (Element) obj;
            Autor autorActual = new Autor();
            autorActual.setIdAutor(eAutor.getAttribute("id").getIntValue());
            autorActual.setNombre(eAutor.getChildText("nombre"));
            autorActual.setApellidosAutor(eAutor.getChildText("apellidos"));
            autores.add(autorActual);
        }
        return autores;
    }

}
