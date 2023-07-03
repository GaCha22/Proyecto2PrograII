package cr.ac.ucr.ie.prograii.service;


import cr.ac.ucr.ie.prograii.model.*;
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

public class LibroDAO {
    private Document document;
    private Element root;
    private String path;

    public LibroDAO(String rootName, String path) throws IOException {
        this.root = new Element(rootName);
        this.path = path;
        this.document = new Document(root);
        guardar();
    }

    public static LibroDAO crearDocumento(String documentPath) throws IOException {
        return new LibroDAO("libros",documentPath);
    }

    private LibroDAO(String documentPath) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);
        this.document = saxBuilder.build(documentPath);
        this.root = document.getRootElement();
        this.path = documentPath;
    }

    public static LibroDAO abrirDocumento(String documentPath) throws IOException, JDOMException {
        return new LibroDAO(documentPath);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document,new FileWriter(this.path));
        //Extra
        xmlOutputter.output(this.document,System.out);
    }

    public void insertarLibro(Libro libro) throws IOException {
        Element eLibro = new Element("libro");
        eLibro.setAttribute("id", String.valueOf(libro.getIdLibro()));

        Element eTitulo = new Element("titulo");
        eTitulo.addContent(libro.getTitulo());
        eLibro.addContent(eTitulo);

        Element eIsbn = new Element("isbn");
        eIsbn.addContent(libro.getIsbn());
        eLibro.addContent(eIsbn);

        List<Autor> autores = libro.getAutores();

        for (Autor autor : autores) {
            Element eAutor = new Element("autor");

            Element eNombre = new Element("nombre");
            eNombre.addContent(autor.getNombre());
            eAutor.addContent(eNombre);

            Element eApellidos = new Element("apellido");
            eApellidos.addContent(autor.getApellidosAutor());
            eAutor.addContent(eApellidos);
            eLibro.addContent(eAutor);
        }

        Element eItems = new Element("items");
        List<LibroItem> libroItems = libro.getItems();
        for (LibroItem libroItem : libroItems) {
            Element eItem = new Element("item");
            eItem.setAttribute("numInscripcion", String.valueOf(libroItem.getNumInscripcion()));
            eItem.setAttribute("estado", String.valueOf(libroItem.isEstado()));
            eItems.addContent(eItem);
        }
        eLibro.addContent(eItems);

        Element eEditorial = new Element("editorial");

        eEditorial.addContent(libro.getEditorial().getNombreEditorial());
        eLibro.addContent(eEditorial);

        Element eTematica = new Element("tematica");
        eTematica.addContent(libro.getTematica().getNombreTematica());
        eLibro.addContent(eTematica);

        root.addContent(eLibro);
        guardar();
    }
    public ArrayList<Libro> getLibros() throws DataConversionException {
        List<Element> eListaLibros = root.getChildren();
        ArrayList<Libro> libros = new ArrayList<>();

        for (Element eLibro : eListaLibros) {
            Libro libroActual = new Libro();
            libroActual.setIdLibro(Integer.parseInt(eLibro.getAttributeValue("id")));
            libroActual.setTitulo(eLibro.getChildText("titulo"));
            libroActual.setIsbn(eLibro.getChildText("isbn"));
            List<Element> eAutores = eLibro.getChildren("autor");
            List<Autor> autores = new ArrayList<>();

            for (Element eAutor : eAutores) {
                Autor autor = new Autor();
                autor.setIdAutor(Integer.parseInt(eAutor.getAttributeValue("id")));
                autor.setNombre(eAutor.getChildText("nombre"));
                autor.setApellidosAutor(eAutor.getChildText("apellido"));
                autores.add(autor);
            }

            libroActual.setAutores(autores);

            List<Element> eItems = eLibro.getChild("items").getChildren("item");
            List<LibroItem> items = new ArrayList<>();

            for (Element eItem : eItems) {
                int numInscripcion = Integer.parseInt(eItem.getAttributeValue("numInscripcion"));
                boolean estado = Boolean.parseBoolean(eItem.getAttributeValue("estado"));
                LibroItem item = new LibroItem(numInscripcion, estado);
                items.add(item);
            }

            libroActual.setItems(items);
            Editorial editorial = new Editorial();
            editorial.setNombreEditorial(eLibro.getChildText("editorial"));
            libroActual.setEditorial(editorial);
            Tematica tematica = new Tematica();
            tematica.setNombreTematica(eLibro.getChildText("tematica"));
            libroActual.setTematica(tematica);

            libros.add(libroActual);
        }
        return libros;
    }}