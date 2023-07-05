<%@ page import="cr.ac.ucr.ie.prograii.model.Autor" %>
<%@ page import="cr.ac.ucr.ie.prograii.service.AutorDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Autor</title>
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

        table {
            font-size: 20px;
            color: #ffffff;
            border-collapse: collapse;
            width: 300px;
            margin: 50px;
        }

        th,
        td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #553dad;
        }

        table a {
            color: #fff;
            text-decoration: none;
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
</head>
<body>
<div class="container">
    <div style="overflow: auto; max-height: 300px; width: 400px; margin: 50px;">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
            </tr>
            </thead>
            <tbody>
            <%
                ArrayList<Autor> autores = AutorDAO.abrirDocumento("autores.xml").getAutores();
                for (Autor autor : autores) {
            %>
            <tr>
                <td><%= autor.getIdAutor() %></td>
                <td><a href="editar_autor.jsp"><%= autor.getNombre() + " " + autor.getApellidosAutor() %></a></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <div class="button-container">
        <form action="insertar_autor.jsp">
            <button type="submit">Añadir Autor</button>
        </form>
        <form action="eliminar_autor.jsp" method="post">
            <button type="submit">Eliminar Autor</button>
        </form>
        <form action="editar_autor.jsp" method="post">
            <button type="submit">Editar Autor</button>
        </form>
        <form action="/prograii">
            <button type="submit">Atrás</button>
        </form>
    </div>
</div>
</body>
</html>
