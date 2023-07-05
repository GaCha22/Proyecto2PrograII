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
import java.util.*;

@WebServlet("/libro/insertarLibro")
public class InsertarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idLibro = Integer.parseInt(req.getParameter("idLibro"));
        String titulo = req.getParameter("titulo");
        int codAutor = Integer.parseInt(req.getParameter("codAutor"));
        int codTematica = Integer.parseInt(req.getParameter("codTematica"));
        int codEditorial = Integer.parseInt(req.getParameter("codEditorial"));

        Libro libro = new Libro();
        libro.setIdLibro(idLibro);
        libro.setTitulo(titulo);

        Tematica tematica = new Tematica();
        tematica.setIdTipo(codTematica);
        libro.setTematica(tematica);

        Autor autor = new Autor();
        autor.setIdAutor(codAutor);
        libro.setAutores(new ArrayList<>());

        Editorial editorial = new Editorial();
        editorial.setIdEditorial(codEditorial);
        libro.setEditorial(editorial);

        try {
            LibroDAO.abrirDocumento("libros.xml").insertarLibro(libro);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

}
