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

@WebServlet("/insertarEditorial")
public class InsertarEditorial extends HttpServlet {
    private EditorialDAO editorialDAO;
    private Editorial nuevaEditorial;
    private String path = "editoriales.xml";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()) {




            String nombre = req.getParameter("nombre");
            String ciudad = req.getParameter("ciudad");

            // trim para comparar
            String nombreSpaces = nombre.trim();
            String ciudadSpaces = ciudad.trim();

            /*
            if(nombreSpaces.equalsIgnoreCase(ciudadSpaces)){
                out.println("<h1 style=\"text-align: center;\">Error, ya existe esta editorial</h1>");
                out.println("<form action=\"editorial/editorial.jsp\">");
                out.println("    <button type=\"submit\">Volver al inicio</button>");
                out.println("</form>");
            }
             */

            if (!nombre.isEmpty() && !ciudad.isEmpty()) {

                try {
                    editorialDAO = EditorialDAO.abrirDocumento(path);
                } catch (Exception e) {
                    String error = e.getMessage();
                    out.println("<h1>"+error+"</h1>");
                    return;
                }

                System.out.println(nombre);
                System.out.println(ciudad);


                int nuevoID = Integer.parseInt(editorialDAO.generarNuevoId());

                nuevaEditorial = new Editorial(nuevoID, nombre, ciudad);

                // insertar en el xml
                editorialDAO.insertarEditorial(nuevaEditorial);

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Insertar Editorial</title>");
                out.println("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../estilox.css/editar.css\">");
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
                out.println("        <h1>Autor Ingresado Correctamente</h1>");
                out.println("       <div class=\"button-container mb-4\">");
                out.println("       <form action=\"editorial.jsp\">");
                out.println("           <button type=\"submit\">Atrás</button>");
                out.println("       </form>");
                out.println("       </div>");
                out.println("     </div>");
                out.println("    </body>");
                out.println("</html>");


            }else
                out.println("<h1>Error al insertar</h1>");



        }

    }

}
