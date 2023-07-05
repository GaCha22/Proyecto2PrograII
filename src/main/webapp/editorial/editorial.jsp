<%@ page import="cr.ac.ucr.ie.prograii.service.EditorialDAO" %>
<%@ page import="cr.ac.ucr.ie.prograii.model.Editorial" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    EditorialDAO editorialDAO = null;

    editorialDAO = EditorialDAO.abrirDocumento("editoriales.xml");

    ArrayList<Editorial> editoriales = editorialDAO.getEditoriales();

%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Editorial</title>
    <link rel="stylesheet" type="text/css" href="../estilox.css/editar.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">


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

        .form-group {
            margin-bottom: 10px;
        }

        select, input, button {
            font-size: 20px;
            padding: 10px;
        }

        .button-container button {
            background-color: #3dad8d;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            padding: 12px 20px;
            font-size: 18px;
        }

        .button-container button:first-child {
            margin-right: 10px;
        }

        .error-message {
            color: #fff;
            margin-top: 10px;
        }



    </style>

</head>
    <body>
        <div class="container text-center">
                <div>
                    <form action="insertarEditorial.jsp" method="POST">
                        <button type="submit" class="btn btn-success mb-5">Añadir</button>
                    </form>
                    <form action="editarEditorial.jsp" method="POST">
                        <button type="submit" class="btn btn-success mb-5">Editar</button>
                    </form>
                    <form action="eliminarEditorial.jsp" method="POST">
                        <button type="submit" class="btn btn-success mb-5">Eliminar</button>
                    </form>
                    <form action="../pagina_principal.jsp" method="post">
                        <button type="submit">Atras</button>
                    </form>
                </div>

        </div>
        <div style="overflow: auto; max-height: 300px; width: 400px; margin: 50px;">

            <table class="table table-sm table-dark table-bordered table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Ciudad</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Editorial editorial : editoriales) {
                %>
                <tr>
                    <td><%= editorial.getIdEditorial() %></td>
                    <td><a href="editarEditorial.jsp"><%= editorial.getNombreEditorial() %></a></td>
                    <td><%= editorial.getCiudad() %></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>

    </body>
</html>