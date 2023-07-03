package cr.ac.ucr.ie.prograii.servlets.autor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/insercionfinalizada")
public class InsertarAutorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Autor insertado</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("<h1>En Construcción</h1>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreAutor = req.getParameter("Nombre");
        String apellidosAutor = req.getParameter("Apellidos");
        String idAutor = req.getParameter("ID Autor");
        String nacionalidad = req.getParameter("Nacionalidad");
    }
}
