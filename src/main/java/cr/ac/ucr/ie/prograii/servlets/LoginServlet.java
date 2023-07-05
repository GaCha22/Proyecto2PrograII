package cr.ac.ucr.ie.prograii.servlets;

import cr.ac.ucr.ie.prograii.service.SessionService;
import cr.ac.ucr.ie.prograii.service.SessionServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "LibreriaMAGA";
    final static String PASSWORD = "MAGA123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionService getSession = new SessionServiceImp();
        Optional<String> sessionOptional = getSession.getUser(req);

        if (sessionOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Sesion ya iniciada</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Bienvenido: " + sessionOptional.get() + " inició sesión correctamente.</h1>");
                out.println("        <p><a href=\"/session-webapp/index.html\">Menu</a></p>");
                out.println("        <p><a href=\"/session-webapp/logout\">Logout</a></p>");
                out.println("    </body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user",username);
            resp.sendRedirect(req.getContextPath()+"/pagina_principal.jsp");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Ingreso no autorizado!");
        }
    }

}
