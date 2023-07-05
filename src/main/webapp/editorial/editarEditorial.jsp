<%@ page import="cr.ac.ucr.ie.prograii.service.EditorialDAO" %>
<%@ page import="cr.ac.ucr.ie.prograii.model.Editorial" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Editar</title>
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

        select, input, button {
            font-size: 20px;
            padding: 10px;
        }

        .button-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
        }

        .button-container button {
            margin-top: 10px;
            margin-bottom: 10px;
            background-color: #3dad8d;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            padding: 12px 20px;
            font-size: 18px;
        }

        .button-container .edit-button {
            margin-top: 10px;
            margin-bottom: 10px;
            background-color: #3dad8d;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            padding: 12px 20px;
            font-size: 18px;
        }

        label {
            color: #fff;
        }

        h1 {
            color: #fff;
            margin-bottom: 10px;
        }

        .search-bar {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .search-bar input[type="text"] {
            font-size: 18px;
            padding: 8px;
            margin-right: 10px;
            width: 200px;
        }

        .search-bar button {
            background-color: #3dad8d;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            padding: 10px 16px;
            font-size: 18px;
        }

        .error-message {
            color: #fff;
        }
    </style>

    <script>
        function validateForm() {
            var nombre = document.getElementById("nombre").value;
            var ciudad = document.getElementById("ciudad").value;

            var errorMessage = "";

            if (nombre === "" || ciudad === "") {
                errorMessage = "Por favor, complete todos los campos.";
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
    <h1>Editar Editorial</h1>

    <div class="button-container">
        <form action="./editarEditorial" method="post" onsubmit="return validateForm()">
            <div class="mb-5">
                <%
                    EditorialDAO editorialDAO;
                    editorialDAO = EditorialDAO.abrirDocumento("editoriales.xml");
                    List<Editorial> editoriales= editorialDAO.getEditoriales();
                %>
                <br>
                <select name="editorial">
                    <% for(Editorial editorial : editoriales) { %>
                    <option value="<%= editorial.getIdEditorial() %>">
                        <%= "ID:  "+ editorial.getIdEditorial() + "  Editorial:  " + editorial.getNombreEditorial() + "   Ciudad: " + editorial.getCiudad()%>
                    </option>
                    <% } %>
                </select>
                <br>
            </div>
            <div>
                <label for="nombre">Nombre</label>
            </div>
            <div class="mb-3">
                <input type="text" placeholder="Nombre de la Editorial" name="nombre" id="nombre">
            </div>
            <div>
                <label for="ciudad">Ciudad</label>
            </div>
            <div class="mb-3">
                <input type="text" placeholder="Ciudad" name="ciudad" id="ciudad">
            </div>
            <div class="mb-3">
                <input type="submit" value="Editar" class="edit-button">
            </div>

            <div id="error-message" class="error-message"></div>
        </form>
        <div class="button-container mb-3">
            <form action="editorial/editorial.jsp">
                <button type="submit">Atrás</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>