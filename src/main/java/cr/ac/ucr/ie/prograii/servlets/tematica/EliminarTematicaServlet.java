package cr.ac.ucr.ie.prograii.servlets.tematica;

import cr.ac.ucr.ie.prograii.service.TematicaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/eliminarTematica")
public class EliminarTematicaServlet extends HttpServlet {

    private TematicaDAO tematicaDAO;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreTematicaEliminar = req.getParameter("tematica");
        try {

            resp.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <title>Eliminación</title>");
                out.println("    <style>");
                out.println("        body {");
                out.println("            background-color: #f8f9fa;");
                out.println("            color: #212529;");
                out.println("            font-family: Arial, sans-serif;");
                out.println("            text-align: center;");
                out.println("            padding: 20px;");
                out.println("        }");
                out.println("        h1 {");
                out.println("            color: #007bff;");
                out.println("        }");
                out.println("        a {");
                out.println("            color: #007bff;");
                out.println("            text-decoration: none;");
                out.println("        }");
                out.println("        .container {");
                out.println("            display: flex;");
                out.println("            justify-content: center;");
                out.println("            align-items: center;");
                out.println("            height: 100vh;");
                out.println("            flex-direction: column;");
                out.println("        }");
                out.println("    </style>");
                out.println("</head>");
                out.println("<body>");
                out.println("    <div class=\"container\">");
                if(nombreTematicaEliminar == null || nombreTematicaEliminar.isBlank()){
                    out.println("        <h1>¡Error, no se ha seleccionado una temática!</h1>");
                    out.println("        <p><a href=\"/prograii/index.jsp\">Menú Inicial</a></p>");
                }else{
                    TematicaDAO tematicaDAO = TematicaDAO.abrirDocumento("tematicas.xml");
                    tematicaDAO.eliminarTematica(nombreTematicaEliminar);
                    out.println("        <h1>¡Se ha eliminado la temática con éxito!</h1>");
                     out.println("        <p><a href=\"/prograii/index.jsp\">Menú Inicial</a></p>");
                }
                out.println("    </div>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (IOException | JDOMException e) {

        }
    }
}

