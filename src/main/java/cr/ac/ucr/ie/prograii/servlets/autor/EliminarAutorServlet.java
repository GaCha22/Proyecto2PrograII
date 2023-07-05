package cr.ac.ucr.ie.prograii.servlets.autor;

import cr.ac.ucr.ie.prograii.service.AutorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/eliminar_autor")
public class EliminarAutorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codAutor = Integer.parseInt(req.getParameter("codAutor"));
        try {
            AutorDAO.abrirDocumento("autores.xml").eliminarAutor(codAutor);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Autor eliminado</title>");
            out.println("        <link rel=\"stylesheet\" type=\"text/css\" href=\"estilox.css/inicio.css\">");
            out.println("        <style>");
            out.println("            body {");
            out.println("                margin: 0;");
            out.println("                padding: 0;");
            out.println("                display: flex;");
            out.println("                justify-content: center;");
            out.println("                align-items: center;");
            out.println("                height: 100vh;");
            out.println("                background-color: #22272e;");
            out.println("                font-family: Arial, sans-serif;");
            out.println("            }");
            out.println("            .container {");
            out.println("                display: flex;");
            out.println("                flex-direction: column;");
            out.println("                align-items: center;");
            out.println("                justify-content: center;");
            out.println("                border-radius: 10px;");
            out.println("                padding: 20px;");
            out.println("            }");
            out.println("            .input-container {");
            out.println("                margin-bottom: 20px;");
            out.println("            }");
            out.println("            select, input, button {");
            out.println("                font-size: 20px;");
            out.println("                padding: 10px;");
            out.println("            }");
            out.println("            .button-container {");
            out.println("                display: flex;");
            out.println("                flex-direction: column;");
            out.println("                align-items: center;");
            out.println("            }");
            out.println("            .button-container button {");
            out.println("                margin-bottom: 20px;");
            out.println("                background-color: #553dad;");
            out.println("                color: #fff;");
            out.println("                border: none;");
            out.println("                border-radius: 4px;");
            out.println("                cursor: pointer;");
            out.println("                padding: 12px 20px;");
            out.println("                font-size: 18px;");
            out.println("            }");
            out.println("            h1 {");
            out.println("               color: #fff;");
            out.println("            }");
            out.println("        </style>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("    <div class=\"container\">");
            out.println("        <h1>Autor eliminado correctamente</h1>");
            out.println("       <div class=\"button-container mb-4\">");
            out.println("       <form action=\"/prograii/autor/autor.jsp\">");
            out.println("           <button type=\"submit\">Atrás</button>");
            out.println("       </form>");
            out.println("       </div>");
            out.println("     </div>");
            out.println("    </body>");
            out.println("</html>");
        }
    }
}
