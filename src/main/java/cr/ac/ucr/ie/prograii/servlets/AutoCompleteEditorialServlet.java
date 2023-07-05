package cr.ac.ucr.ie.prograii.servlets;

import cr.ac.ucr.ie.prograii.model.Editorial;
import cr.ac.ucr.ie.prograii.service.EditorialDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/libro/autocompleteEditorial")
public class AutoCompleteEditorialServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String term = req.getParameter("term"); // Obtener el valor del parámetro "term" enviado desde el cliente

            // Obtener todas las temáticas desde el archivo XML
            List<Editorial> editoriales = EditorialDAO.abrirDocumento("C:\\Users\\gabri\\Desktop\\Cosas de progra\\editoriales.xml").getEditoriales();
            System.out.println(editoriales.toString());

            // Filtrar las temáticas que coincidan con el término de búsqueda
            List<Editorial> editorialesFiltradas = new ArrayList<>();
            for (Editorial editorial : editoriales) {
                if (editorial.getNombreEditorial().toLowerCase().contains(term.toLowerCase())) {
                    editorialesFiltradas.add(editorial);
                }
            }

            System.out.println(editorialesFiltradas.toString());

            // Generar la respuesta XML con las temáticas filtradas
            resp.setContentType("application/xml");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(convertEditorialesToXML(editorialesFiltradas));
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertEditorialesToXML(List<Editorial> editoriales) {
        StringBuilder xml = new StringBuilder();
        xml.append("<editoriales>");

        for (Editorial editorialca : editoriales) {
            xml.append("<editorial idEditorial=\"").append(editorialca.getIdEditorial()).append("\" nombre=\"")
                    .append(editorialca.getNombreEditorial()).append("\" />");
        }

        xml.append("</editoriales>");
        return xml.toString();
    }

}
