package cr.ac.ucr.ie.prograii.servlets;

import cr.ac.ucr.ie.prograii.model.Autor;
import cr.ac.ucr.ie.prograii.service.AutorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/autocomplete/autocompleteAutor")
public class AutoCompleteAutorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String term = req.getParameter("term"); // Obtener el valor del parámetro "term" enviado desde el cliente

            // Obtener todas las temáticas desde el archivo XML
            List<Autor> autores = AutorDAO.abrirDocumento("autores.xml").getAutores();
            System.out.println(autores.toString());

            // Filtrar las temáticas que coincidan con el término de búsqueda
            List<Autor> autoresFiltrados = new ArrayList<>();
            for (Autor autor : autores) {
                String nombreCompleto = autor.getNombre() + " " +autor.getApellidosAutor();
                if (nombreCompleto.toLowerCase().contains(term.toLowerCase())) {
                    autoresFiltrados.add(autor);
                }
            }

            System.out.println(autoresFiltrados.toString());

            // Generar la respuesta XML con las temáticas filtradas
            resp.setContentType("application/xml");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(convertAutoresToXML(autoresFiltrados));
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertAutoresToXML(List<Autor> autores) {
        StringBuilder xml = new StringBuilder();
        xml.append("<autores>");

        for (Autor autor : autores) {
            xml.append("<autor idAutor=\"").append(autor.getIdAutor()).append("\" nombre=\"")
                    .append(autor.getNombre()).append(" ").append(autor.getApellidosAutor()).append("\" />");
        }

        xml.append("</autores>");
        return xml.toString();
    }

}
