package cr.ac.ucr.ie.prograii.service;


import cr.ac.ucr.ie.prograii.model.*;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    private Document document;
    private Element root;
    private String path;

    private LibroDAO(String documentPath) throws IOException, JDOMException {
        File file = new File(documentPath);
        if (!file.exists()) {
            //se encarga de crear tanto el DOM y como Documento XML
            this.path = documentPath;
            this.root = new Element("libros");
            this.document = new Document(root);
            guardar();
        }else {
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);
            this.document = saxBuilder.build(documentPath);
            this.root = document.getRootElement();
            this.path = documentPath;
        }
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
        Element eAutores = new Element("autores");

        for (Autor autor : autores) {
            Element eAutor = new Element("autor");

            eAutor.setAttribute("idAutor", String.valueOf(autor.getIdAutor()));
            Element nombre = new Element("nombre");
            nombre.addContent(autor.getNombre());
            Element apellidos = new Element("apellidos");
            apellidos.addContent(autor.getApellidosAutor());
            eAutor.addContent(nombre);
            eAutor.addContent(apellidos);
            eAutores.addContent(eAutor);
        }

        eLibro.addContent(eAutores);
        Element eEditorial = new Element("editorial");

        eEditorial.setAttribute("idEditorial", String.valueOf(libro.getEditorial().getIdEditorial()));
        eEditorial.addContent(libro.getEditorial().getNombreEditorial());
        eLibro.addContent(eEditorial);

        Element eTematica = new Element("tematica");
        eTematica.setAttribute("idTematica", String.valueOf(libro.getTematica().getIdTipo()));
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
            Element eAutores = eLibro.getChild("autores");
            List<Autor> autores = new ArrayList<>();

            if (eAutores != null) {
                List<Element> eListaAutores = eAutores.getChildren("autor");

                for (Element eAutor : eListaAutores) {
                    Autor autor = new Autor();
                    autor.setIdAutor(eAutor.getAttribute("idAutor").getIntValue());
                    autor.setNombre(eAutor.getChildText("nombre"));
                    autor.setApellidosAutor(eAutor.getChildText("apellidos"));
                    autores.add(autor);
                }
            }

            libroActual.setAutores(autores);



            Editorial editorial = new Editorial();
            editorial.setIdEditorial(eLibro.getChild("editorial").getAttribute("idEditorial").getIntValue());
            editorial.setNombreEditorial(eLibro.getChildText("editorial"));
            libroActual.setEditorial(editorial);

            Tematica tematica = new Tematica();
            tematica.setIdTipo(eLibro.getChild("tematica").getAttribute("idTematica").getIntValue());
            tematica.setNombreTematica(eLibro.getChildText("tematica"));
            libroActual.setTematica(tematica);

            libros.add(libroActual);
        }
        return libros;
    }

    public boolean buscarLibro(int idLibro) throws DataConversionException {
        List<Element> eListaLibros = root.getChildren();
        for (Element eLibro : eListaLibros) {
            int codLibroActual = eLibro.getAttribute("id").getIntValue();
            if (codLibroActual == idLibro) return true;
        }
        return false;
    }

    public void eliminarLibro(int idLibro) throws IOException {
        List<Element> eListaLibros = root.getChildren("libro");

        for (Element eLibro : eListaLibros) {
            int id = Integer.parseInt(eLibro.getAttributeValue("id"));
            if (id == idLibro) {
                root.removeContent(eLibro);
                break;
            }
        }
        guardar();
    }

    public void editarLibro(int idLibro, Libro libroActualizado) throws IOException, DataConversionException {
        List<Element> libros = root.getChildren("libro");

        for (Element eLibro : libros) {
            int id = eLibro.getAttribute("id").getIntValue();
            if (id == idLibro) {
                eLibro.getChild("titulo").setText(libroActualizado.getTitulo());
                eLibro.getChild("isbn").setText(libroActualizado.getIsbn());
                eLibro.removeChild("autores");
                Element eAutores = new Element("autores");
                for (Autor autor : libroActualizado.getAutores()) {
                    Element eAutor = new Element("autor");
                    Element nombre = new Element("nombre");
                    Element apellidos = new Element("apellidos");
                    eAutor.setAttribute("idAutor", String.valueOf(autor.getIdAutor()));
                    nombre.addContent(autor.getNombre());
                    apellidos.addContent(autor.getApellidosAutor());
                    eAutor.addContent(nombre);
                    eAutor.addContent(apellidos);
                    eAutores.addContent(eAutor);
                }
                eLibro.addContent(eAutores);
                Element eEditorial = new Element("editorial");
                Element eTematica = new Element("tematica");
                eEditorial.setAttribute("idEditorial", String.valueOf(libroActualizado.getEditorial().getIdEditorial()));
                eEditorial.addContent(libroActualizado.getEditorial().getNombreEditorial());
                eTematica.setAttribute("idTematica", String.valueOf(libroActualizado.getTematica().getIdTipo()));
                eTematica.addContent(libroActualizado.getTematica().getNombreTematica());
                eLibro.removeChildren("editorial");
                eLibro.removeChildren("tematica");
                eLibro.addContent(eEditorial);
                eLibro.addContent(eTematica);
                break;
            }
        }
        guardar();
    }
}