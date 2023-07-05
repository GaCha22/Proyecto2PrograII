<%@ page import="cr.ac.ucr.ie.prograii.service.EditorialDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cr.ac.ucr.ie.prograii.model.Editorial" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Insertar Editorial</title>
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

        .button-container {
            display: flex;
            justify-content: space-between;
            width: 100%;
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
    <div class="container text-center">
        <form action="./insertarEditorial" method="post" onsubmit="return validateForm()">
        <div>
            <label for="nombre" class="form-label text-danger">Nombre</label>
        </div>
        <div>
            <input type="text" name="nombre" id="nombre" class="mb-5">
        </div>

        <div>
            <label for="ciudad" class="form-label text-danger">Ciudad</label>
        </div>
        <div>
            <input type="text" name="ciudad" id="ciudad" class="mb-5">
        </div>
        <div>
            <button type="submit" class="">Guardar</button>
        </div>
        <div id="error-message" class="error-message"></div>

        </form>
    </div>


</body>
</html>