package cr.ac.ucr.ie.prograii.servlets;

import cr.ac.ucr.ie.prograii.model.Autor;
import cr.ac.ucr.ie.prograii.model.Libro;
import cr.ac.ucr.ie.prograii.service.LibroDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/autocomplete/autocompleteLibro")
public class AutoCompleteLibroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String term = req.getParameter("term"); // Obtener el valor del parámetro "term" enviado desde el cliente

            // Obtener todas las temáticas desde el archivo XML
            List<Libro> libros = LibroDAO.abrirDocumento("libros.xml").getLibros();
            System.out.println(libros.toString());

            // Filtrar las temáticas que coincidan con el término de búsqueda
            List<Libro> librosFiltrados = new ArrayList<>();
            for (Libro libro : libros) {
                if (libro.getTitulo().toLowerCase().contains(term.toLowerCase())) {
                    librosFiltrados.add(libro);
                }
            }

            System.out.println(librosFiltrados.toString());

            // Generar la respuesta XML con las temáticas filtradas
            resp.setContentType("application/xml");
            resp.setCharacterEncoding("UTF-8");
            System.out.println(convertLibrosToXML(librosFiltrados));
            resp.getWriter().write(convertLibrosToXML(librosFiltrados));
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertLibrosToXML(List<Libro> libros) {
        StringBuilder xml = new StringBuilder();
        xml.append("<libros>");

        for (Libro libro : libros) {
            List<Autor> autores = libro.getAutores();
            xml.append("<libro idLibro=\"").append(libro.getIdLibro())
                    .append("\" titulo=\"").append(libro.getTitulo())
                    .append("\" isbn=\"").append(libro.getIsbn())
                    .append("\" autor1=\"").append(autores.get(0).getNombre()).append(" ").append(autores.get(0).getApellidosAutor())
                    .append("\" codAutor1=\"").append(autores.get(0).getIdAutor())
                    .append("\" autor2=\"");
            if (autores.size() > 1) xml.append(autores.get(1).getNombre()).append(" ").append(autores.get(1).getApellidosAutor()).append("\" codAutor2=\"").append(autores.get(1).getIdAutor());
            else xml.append(-1).append("\" codAutor2=\"").append(-1);
            xml.append("\" autor3=\"");
            if (autores.size() > 2) xml.append(autores.get(2).getNombre()).append(" ").append(autores.get(2).getApellidosAutor()).append("\" codAutor3=\"").append(autores.get(2).getIdAutor());
            else xml.append(-1).append("\" codAutor3=\"").append(-1);
            xml.append("\" editorial=\"").append(libro.getEditorial().getNombreEditorial())
                    .append("\" codEditorial=\"").append(libro.getEditorial().getIdEditorial())
                    .append("\" tematica=\"").append(libro.getTematica().getNombreTematica())
                    .append("\" codTematica=\"").append(libro.getTematica().getIdTipo())
                    .append("\" />");
        }

        xml.append("</libros>");
        return xml.toString();
    }

}
