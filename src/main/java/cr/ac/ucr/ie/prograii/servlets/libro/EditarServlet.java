package cr.ac.ucr.ie.prograii.servlets.libro;

import cr.ac.ucr.ie.prograii.model.Autor;
import cr.ac.ucr.ie.prograii.model.Editorial;
import cr.ac.ucr.ie.prograii.model.Libro;
import cr.ac.ucr.ie.prograii.model.Tematica;
import cr.ac.ucr.ie.prograii.service.AutorDAO;
import cr.ac.ucr.ie.prograii.service.EditorialDAO;
import cr.ac.ucr.ie.prograii.service.LibroDAO;
import cr.ac.ucr.ie.prograii.service.TematicaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/libro/editarServlet")
public class EditarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codLibro = Integer.parseInt(req.getParameter("codLibro"));
        String titulo = req.getParameter("titulo");
        int codAutor = Integer.parseInt(req.getParameter("codAutor"));
        int codTematica = Integer.parseInt(req.getParameter("codTematica"));
        int codEditorial = Integer.parseInt(req.getParameter("codEditorial"));
        String isbn = req.getParameter("isbn");


        String codAutor1 = req.getParameter("codAutor1");
        String codAutor2 = req.getParameter("codAutor2");

        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setIsbn(isbn);


        Tematica tematica = null;
        try {
            tematica = TematicaDAO.abrirDocumento("tematicas.xml").getTematica(codTematica);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
        libro.setTematica(tematica);

        List<Autor> autores = new ArrayList<>();
        Autor autor = null;
        try {
            autor = AutorDAO.abrirDocumento("autores.xml").getAutor(codAutor);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
        autores.add(autor);

        if ((codAutor1 != null && !codAutor1.isBlank()) && !codAutor1.equals("-1")){
            Autor autor1 = null;
            try {
                autor1 = AutorDAO.abrirDocumento("autores.xml").getAutor(Integer.parseInt(codAutor1));
            } catch (JDOMException e) {
                throw new RuntimeException(e);
            }
            autores.add(autor1);
        }

        if ((codAutor2 != null && !codAutor2.isBlank()) && !codAutor2.equals("-1")){
            Autor autor2 = null;
            try {
                autor2 = AutorDAO.abrirDocumento("autores.xml").getAutor(Integer.parseInt(codAutor2));
            } catch (JDOMException e) {
                throw new RuntimeException(e);
            }
            autores.add(autor2);
        }

        libro.setAutores(autores);

        Editorial editorial = null;
        try {
            editorial = EditorialDAO.abrirDocumento("editoriales.xml").getEditorial(codEditorial);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
        libro.setEditorial(editorial);

        boolean insertado = false;
        try {
            LibroDAO libroDAO = LibroDAO.abrirDocumento("libros.xml");
            if (libroDAO.buscarLibro(codLibro)){
                libroDAO.editarLibro(codLibro, libro);
                insertado = true;
            }
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Autor insertado</title>");
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
            if (insertado) {
                out.println("        <h1>Libro editado correctamente</h1>");
                out.println("       <div class=\"button-container mb-4\">");
                out.println("       <form action=\"/prograii/libro/buscarLibro\">");
                out.println("           <button type=\"submit\">Atrás</button>");
            }else {
                out.println("        <h1>El libro a editar no existe</h1>");
                out.println("       <div class=\"button-container mb-4\">");
                out.println("       <form action=\"/prograii/libro/insertar.jsp\">");
                out.println("           <button type=\"submit\">Atrás</button>");
                out.println("       </form>");
            }
            out.println("       </div>");
            out.println("     </div>");
            out.println("    </body>");
            out.println("</html>");
        }
    }
}
