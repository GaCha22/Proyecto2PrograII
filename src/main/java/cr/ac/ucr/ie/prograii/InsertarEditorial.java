package cr.ac.ucr.ie.prograii;

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

            out.println("       <title>antes del IF</title>");
            int i=0;
            if (nombre != null && ciudad != null) {

                System.out.println(nombre);
                System.out.println(ciudad);


                try {
                    editorialDAO = EditorialDAO.abrirDocumento(path);
                } catch (Exception e) {
                    String error = e.getMessage();
                    out.println("<h1>"+error+"</h1>");
                    return;
                }


                nuevaEditorial = new Editorial(i, nombre, ciudad);

                // insertar en el xml
                editorialDAO.insertarEditorial(nuevaEditorial);


                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>Insertado</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("        <h1>Insertado con éxito</h1>");
                out.println("   </body>");
                out.println("   </html>");






                i++;
            }
        }




    }

}
