<%@ page import="cr.ac.ucr.ie.prograii.service.TematicaDAO" %>
<%@ page import="cr.ac.ucr.ie.prograii.model.Tematica" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Tematica</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="estilox.css/prueba.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: Arial, sans-serif;
            color: #333333;
            background-color: #f1f1f1;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            border-radius: 10px;
            padding: 20px;
            background-color: #ffffff;
            max-width: 400px;
            width: 100%;
            border: 1px solid #ccc;
        }

        h1 {
            color: #553dad;
            font-size: 24px;
            margin-top: 0;
        }

        .form-group {
            margin-bottom: 10px;
        }

        select, input, button {
            font-size: 20px;
            padding: 10px;
            width: 100%;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .button-container {
            display: flex;
            justify-content: center;
            width: 100%;
            margin-top: 10px;
        }

        .button-container button {
            background-color: #553dad;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            padding: 12px 20px;
            font-size: 18px;
            width: 48%;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Editar una temática</h1>
    <form action="/prograii/editarTematica" method="post">
        <div class="form-group mb-4">
            <h1>Seleccione la temática que desea editar.</h1>
        </div>
        <div>
            <%
                TematicaDAO tematicaDAO;
                tematicaDAO= TematicaDAO.abrirDocumento("tematicas.xml");
                List<Tematica> tematicas= tematicaDAO.getTematicas();
            %>
            <br>
            <select name="tematica">
                <% for(Tematica tematica : tematicas) { %>
                <option value="<%= tematica.getNombreTematica() %>">
                    <%= tematica.getNombreTematica()%>
                </option>
                <% } %>
            </select>
            <br>
        </div>
        <div class="form-group mb-4">
            <label for="newNombre" style="color: #333333;">Nuevo nombre temática</label>
            <div>
                <input type="text" name="newNombre" id="newNombre">
            </div>
        </div>
        <div class="button-container">
            <button type="submit">Editar</button>
        </div>
    </form>
    <div class="button-container">
    <form action="tematica.jsp">
        <button type="submit">Atrás</button>
    </form>
    </div>
</div>
</body>
</html>
