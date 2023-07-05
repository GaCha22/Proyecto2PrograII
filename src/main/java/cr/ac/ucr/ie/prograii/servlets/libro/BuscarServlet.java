package cr.ac.ucr.ie.prograii.servlets.libro;

import cr.ac.ucr.ie.prograii.model.Autor;
import cr.ac.ucr.ie.prograii.model.Libro;
import cr.ac.ucr.ie.prograii.service.LibroDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/libro/buscarLibro")
public class BuscarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("buscar", false);
        req.getRequestDispatcher("libro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //titulo, autor, tema
        String buscar = req.getParameter("search");
        int valorABuscar = Integer.parseInt(req.getParameter("tipo"));
        List<Libro> librosFiltrados = new ArrayList<>();
        List<Libro> listaLibros;
        try {
            listaLibros = LibroDAO.abrirDocumento("libros.xml").getLibros();
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }

        for (Libro libro : listaLibros) {
            switch (valorABuscar){
                case 1:
                    if (libro.getTitulo().contains(buscar)) librosFiltrados.add(libro);
                    break;
                case 2:
                    List<Autor> autores = libro.getAutores();
                    for (Autor autor :
                            autores) {
                        if ((autor.getNombre() + " " + autor.getApellidosAutor()).contains(buscar)) {
                            librosFiltrados.add(libro);
                            break;
                        }
                    }
                    break;
                case 3:
                    if (libro.getTematica().getNombreTematica().contains(buscar)) librosFiltrados.add(libro);
                    break;
            }
        }

        req.setAttribute("buscar", true);
        req.setAttribute("lista", librosFiltrados);
        req.getRequestDispatcher("libro.jsp").forward(req, resp);

    }
}
