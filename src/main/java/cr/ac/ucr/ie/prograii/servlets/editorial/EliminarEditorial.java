package cr.ac.ucr.ie.prograii.servlets.editorial;

import cr.ac.ucr.ie.prograii.model.Editorial;
import cr.ac.ucr.ie.prograii.service.EditorialDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

@WebServlet("/eliminarEditorial")
public class EliminarEditorial extends HttpServlet {
    private EditorialDAO editorialDAO;
    private String path = "editoriales.xml";
    ;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()) {

            // abrir el doc xml
            try {
                editorialDAO = EditorialDAO.abrirDocumento(path);
            } catch (Exception e) {
                String error = e.getMessage();
                out.println("<h1>" + error + "</h1>");
                return;
            }

            String id = req.getParameter("id");
            boolean encontrado = editorialDAO.buscar(id);

            if(encontrado){
                editorialDAO.eliminarEditorial(id);

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>Insertado</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("<h1 style=\"text-align: center;\">Eliminado con éxito</h1>");
                out.println("<form action=\"editorial/editorial.jsp\">");
                out.println("    <button type=\"submit\">Volver al inicio</button>");
                out.println("</form>");
                out.println("   </body>");
                out.println("   </html>");


            }else{
                out.println("<h1 style=\"text-align: center;\">No se pudo eliminar</h1>");


            }

        }
    }
}
