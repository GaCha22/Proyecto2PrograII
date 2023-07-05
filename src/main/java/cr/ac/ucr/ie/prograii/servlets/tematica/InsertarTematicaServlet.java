package cr.ac.ucr.ie.prograii.servlets.tematica;

import cr.ac.ucr.ie.prograii.model.Tematica;
import cr.ac.ucr.ie.prograii.service.TematicaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.DataConversionException;
import org.jdom2.JDOMException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/agregarTematica")
public class InsertarTematicaServlet extends HttpServlet {
    static boolean flag = false;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nombre = req.getParameter("nombre"); //Obtener el valor del parámetro "nombre" enviado desde el cliente
            TematicaDAO tematicaDAO;
            //Obtener todas las temáticas desde el archivo XML
            List<Tematica> tematicas = TematicaDAO.abrirDocumento("tematicas.xml").getTematicas();
            List<Tematica> tematicasExistentes = new ArrayList<>();
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <title>Inserción Temática</title>");
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
                if (nombre == null || nombre.isBlank()) {
                    out.println("        <h1>Error debe ingresar un nombre!</h1>");
                    out.println("<p><a href=\"/prograii/insertarTematica.jsp\">Insertar temática</a></p>");
                    out.println("    </div>");
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    for (Tematica tematica : tematicas) {
                        if (tematica.getNombreTematica().toLowerCase().contains(nombre.toLowerCase())) {
                            out.println("        <h1>La temática ya existe!</h1>");
                            out.println("<p><a href=\"/prograii/insertarTematica.jsp\">Insertar otra temática</a></p>");
                            flag = true;
                            break;
                        }
                    }
                    if (flag == true) {
                        flag = false;
                    } else {
                        tematicaDAO = TematicaDAO.abrirDocumento("tematicas.xml");
                        int contador = tematicas.size() + 1; //Incrementar el contador en función del tamaño actual de las temáticas
                        Tematica tematica = new Tematica(contador, nombre);
                        tematicaDAO.insertarTematica(tematica);
                        out.println("        <h1>Se agregó correctamente la temática!</h1>");
                        out.println("        <p><a href=\"/prograii/index.jsp\">Menú Inicial</a></p>");
                    }
                }
            } catch (JDOMException e) {
                throw new RuntimeException(e);
            }

        } catch (DataConversionException e) {
            throw new RuntimeException(e);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }}