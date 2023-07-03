package cr.ac.ucr.ie.prograii.servlets;

import cr.ac.ucr.ie.prograii.model.Tematica;
import cr.ac.ucr.ie.prograii.service.TematicaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/autocompleteTematica")
public class AutoCompleteTematicaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String term = req.getParameter("term"); // Obtener el valor del parámetro "term" enviado desde el cliente

            // Obtener todas las temáticas desde el archivo XML
            List<Tematica> tematicas = TematicaDAO.abrirDocumento("C:\\Users\\gabri\\Desktop\\Cosas de progra\\tematicas.xml").getTematicas();
            System.out.println(tematicas.toString());

            // Filtrar las temáticas que coincidan con el término de búsqueda
            List<Tematica> tematicasFiltradas = new ArrayList<>();
            for (Tematica tematica : tematicas) {
                if (tematica.getNombreTematica().toLowerCase().contains(term.toLowerCase())) {
                    tematicasFiltradas.add(tematica);
                }
            }

            System.out.println(tematicasFiltradas.toString());

            // Generar la respuesta XML con las temáticas filtradas
            resp.setContentType("application/xml");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(convertTematicasToXML(tematicasFiltradas));
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertTematicasToXML(List<Tematica> tematicas) {
        StringBuilder xml = new StringBuilder();
        xml.append("<tematicas>");

        for (Tematica tematica : tematicas) {
            xml.append("<tematica idTipo=\"").append(tematica.getIdTipo()).append("\" nombre=\"")
                    .append(tematica.getNombreTematica()).append("\" />");
        }

        xml.append("</tematicas>");
        return xml.toString();
    }

}
