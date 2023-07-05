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
            resp.getWriter().write(convertLibrosToXML(librosFiltrados));
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertLibrosToXML(List<Libro> autores) {
        StringBuilder xml = new StringBuilder();
        xml.append("<libros>");

        for (Libro libro : autores) {
            xml.append("<libro idLibro=\"").append(libro.getIdLibro()).append("\" titulo=\"")
                    .append(libro.getTitulo()).append("\" />");
        }

        xml.append("</libros>");
        return xml.toString();
    }

}
