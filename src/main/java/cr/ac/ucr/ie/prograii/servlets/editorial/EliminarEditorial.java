package cr.ac.ucr.ie.prograii.servlets.editorial;

import cr.ac.ucr.ie.prograii.model.Editorial;
import cr.ac.ucr.ie.prograii.service.EditorialDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.DataConversionException;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

@WebServlet("/editorial/eliminarEditorial")
public class EliminarEditorial extends HttpServlet {
    private EditorialDAO editorialDAO;
    private String path = "editoriales.xml";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Eliminar Editorial</title>");
            out.println("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../estilox.css/editar.css\">");

            out.println("<style>");
            out.println("    body {");
            out.println("        margin: 0;");
            out.println("        padding: 0;");
            out.println("        display: flex;");
            out.println("        justify-content: center;");
            out.println("        align-items: center;");
            out.println("        height: 100vh;");
            out.println("        background-color: #22272e;");
            out.println("        font-family: Arial, sans-serif;");
            out.println("    }");
            out.println("");
            out.println("    .container {");
            out.println("        display: flex;");
            out.println("        flex-direction: column;");
            out.println("        align-items: center;");
            out.println("        justify-content: center;");
            out.println("        border-radius: 10px;");
            out.println("        padding: 20px;");
            out.println("    }");
            out.println("");
            out.println("");
            out.println("    select, input, button {");
            out.println("        font-size: 20px;");
            out.println("        padding: 10px;");
            out.println("    }");
            out.println("");
            out.println("     .button-container button {");
            out.println("        background-color: #3dad8d;");
            out.println("        color: #fff;");
            out.println("        border: none;");
            out.println("        border-radius: 4px;");
            out.println("        cursor: pointer;");
            out.println("        padding: 12px 20px;");
            out.println("        font-size: 18px;");
            out.println("    }");
            out.println("");
            out.println("    .button-container button:first-child {");
            out.println("        margin-right: 10px;");
            out.println("    }");
            out.println("");
            out.println("    .error-message {");
            out.println("        color: #fff;");
            out.println("        margin-top: 10px;");
            out.println("    }");
            out.println("    h1 {");
            out.println("        color: white;");
            out.println("    }");
            out.println("    label {");
            out.println("        color: white;");
            out.println("    }");
            out.println("</style>");


            out.println("    </head>");
            out.println("    <body>");

            // abrir el doc xml
            try {
                editorialDAO = EditorialDAO.abrirDocumento(path);
            } catch (Exception e) {
                String error = e.getMessage();
                out.println("<h1>" + error + "</h1>");
                return;
            }

            String id = req.getParameter("codEditorial");
            boolean encontrado = false;


            if(id != null && !id.isBlank()){
                try {
                    encontrado = editorialDAO.buscar(Integer.parseInt(id));
                } catch (DataConversionException e) {
                    throw new RuntimeException(e);
                }

            }


            if(encontrado){
                editorialDAO.eliminarEditorial(Integer.parseInt(id));


                out.println("    <div class=\"container\">");
                out.println("        <h1>Editorial Eliminado</h1>");
                out.println("       <div class=\"button-container mb-4\">");
                out.println("       <form action=\"editorial.jsp\">");
                out.println("           <button type=\"submit\">Atrás</button>");
                out.println("       </form>");
                out.println("       </div>");
                out.println("     </div>");


            }else{
                out.println("    <div class=\"container\">");
                out.println("        <h1>Error al eliminar</h1>");
                out.println("       <div class=\"button-container mb-4\">");
                out.println("       <form action=\"editorial.jsp\">");
                out.println("           <button type=\"submit\">Atrás</button>");
                out.println("       </form>");
                out.println("     </div>");
                out.println("     </div>");

            }
            out.println("    </body>");
            out.println("</html>");

        }
    }
}
