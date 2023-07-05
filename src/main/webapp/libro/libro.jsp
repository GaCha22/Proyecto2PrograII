<%@ page import="cr.ac.ucr.ie.prograii.service.LibroDAO" %>
<%@ page import="cr.ac.ucr.ie.prograii.model.Libro" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Libro> libros;
    boolean buscar = false;
    buscar = (boolean)request.getAttribute("buscar");
    if (buscar){
        libros = (List<Libro>)request.getAttribute("lista");
    }else {
        LibroDAO libroDAO = null;

        libroDAO = LibroDAO.abrirDocumento("libros.xml");

        libros = libroDAO.getLibros();
    }

%>
<html>
<head>
    <title>Autor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../estilox.css/inicio.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #22272e;
            font-family: Arial, sans-serif;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            border-radius: 10px;
            padding: 20px;
        }

        .input-container {
            margin-bottom: 20px;
        }

        select, input, button {
            font-size: 20px;
            padding: 10px;
        }

        .button-container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .button-container button {
            margin-bottom: 20px;
            background-color: #553dad;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            padding: 12px 20px;
            font-size: 18px;
        }
    </style>
    <script>
        function validateForm() {
            var search = document.getElementById("search").value;


            var errorMessage = "";

            if (search === "") {
                errorMessage = "Por favor, complete el campo para buscar.";
            }

            if (errorMessage !== "") {
                document.getElementById("error-message").innerText = errorMessage;
                return false;
            }
        }
    </script>
</head>
<body>
<div class="container">
    <div>
        <form action="./buscarLibro" method="post" onsubmit="return validateForm()">
            <div id="error-message" class="error-message"></div>
            <select name="tipo">
                <option value="1">Titulo</option>
                <option value="2">Autor</option>
                <option value="3">Tematica</option>
            </select>
            <input name="search" id="search" placeholder="Buscar">
            <button type="submit">Buscar</button>
        </form>
    </div>
    <div class="button-container">
        <form action="insertar.jsp">
            <button type="submit">Añadir Libro</button>
        </form>
        <form action="eliminar.jsp" method="post">
            <button type="submit">Eliminar Libro</button>
        </form>
        <form action="editar.jsp" method="post">
            <button type="submit">Editar Libro</button>
        </form>
        <form action="../pagina_principal.jsp" method="post">
            <button type="submit">Atras</button>
        </form>
    </div>
    <div style="overflow: auto; max-height: 300px; width: 400px; margin: 50px;">

        <table class="table table-sm table-dark table-bordered table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>ISBN</th>
                <th>TITULO</th>
                <th>AUTOR 1</th>
                <th>AUTOR 2</th>
                <th>AUTOR 3</th>
                <th>TEMATICA</th>
                <th>EDITORIAL</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Libro editorial : libros) {
            %>
            <tr>
                <td><%= editorial.getIdLibro() %></td>
                <td><%= editorial.getIsbn() %></td>
                <td><%= editorial.getTitulo() %></td>
                <td><%= editorial.getAutores().get(0).getNombre() + " " + editorial.getAutores().get(0).getApellidosAutor() %></td>
                <%if (editorial.getAutores().size() > 1){%>
                    <td><%= editorial.getAutores().get(1).getNombre() + " " + editorial.getAutores().get(1).getApellidosAutor() %></td>
                <%}else {%>
                    <td>N/T</td>
                <%}%>
                <%if (editorial.getAutores().size() > 2){%>
                    <td><%= editorial.getAutores().get(2).getNombre() + " " + editorial.getAutores().get(2).getApellidosAutor() %></td>
                <%}else {%>
                    <td>N/T</td>
                <%}%>
                <td><%= editorial.getTematica().getNombreTematica() %></td>
                <td><%= editorial.getEditorial().getNombreEditorial() %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
