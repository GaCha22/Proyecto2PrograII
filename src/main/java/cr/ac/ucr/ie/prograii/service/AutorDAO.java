package cr.ac.ucr.ie.prograii.service;

import cr.ac.ucr.ie.prograii.model.Autor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        return new AutorDAO("autores", documentPath);
    }

    private AutorDAO(String documentPath) throws IOException, JDOMException {
        File file = new File(documentPath);
        if (!file.exists()) {
            //se encarga de crear tanto el DOM y como Documento XML
            this.path = documentPath;
            this.root = new Element("autores");
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


    public static AutorDAO abrirDocumento(String documentPath) throws IOException, JDOMException {
        return new AutorDAO(documentPath);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document,new FileWriter(this.path));
    }

    public void insertarAutor(Autor autor) throws IOException {
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

    public boolean buscarAutor(int codAutor){
        boolean autorExiste = false;
        List eListaAutores = root.getChildren();
        for(Object obj: eListaAutores) {
            Element eAutor = (Element)obj;
            if(Integer.parseInt(eAutor.getAttributeValue("id")) == codAutor){
                autorExiste = true;
                break;
            }
        }
        return autorExiste;
    }

    public Autor getAutor(int codAutor) throws DataConversionException {
        Autor autor = new Autor();
        List eListaAutores = root.getChildren();

        for(Object object: eListaAutores){

            Element eAutor = (Element) object;
            autor.setIdAutor(eAutor.getAttribute("id").getIntValue());
            if(autor.getIdAutor()==codAutor){
                autor.setNombre(eAutor.getChildText("nombre"));
                autor.setApellidosAutor(eAutor.getChildText("apellidos"));
            }
        }
        return autor;

    }

    public void eliminarAutor(int codAutor) throws IOException {
        List<Element> autores = root.getChildren("autor");

        for (Element autor : autores) {
            int id = Integer.parseInt(autor.getAttributeValue("id"));
            if (id == codAutor) {
                root.removeContent(autor);
                break;
            }
        }
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
