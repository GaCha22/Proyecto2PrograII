<%@ page import="cr.ac.ucr.ie.prograii.service.AutorDAO" %>
<%@ page import="cr.ac.ucr.ie.prograii.model.Autor" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Insertar Autor</title>
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

    select, input, button {
      font-size: 20px;
      padding: 10px;
    }

    .button-container {
      margin-top: 10px;
      margin-bottom: 10px;
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    .button-container button {
      background-color: #553dad;
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
      background-color: #553dad;
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
    }

    .error-message {
      color: #fff;
    }

  </style>

  <script>
    function validateForm() {
      var nombre = document.getElementById("nombre").value;
      var apellidos = document.getElementById("apellidos").value;
      var id = document.getElementById("id").value;

      var errorMessage = "";

      if (nombre === "" || apellidos === "" || id === "") {
        errorMessage = "Por favor, complete todos los campos.";
      } else if (!/^\d+$/.test(id)) {
        errorMessage = "El ID del autor solo debe contener números.";
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
  <h1>Editar Autor</h1>
  <div class="button-container">
    <form action="/prograii/editar_autor" method="post">
        <div>
          <label for="autor">Nombre del Autor</label>
          <div>
            <input type="text" placeholder="Autor" name="autor" id="autor" data-autocomplete="true" autocomplete="on">
            <input type="hidden" name="codAutor" id="codAutor">
          </div>
        </div>
      <div>
        <label for="apellidos">Nuevo apellidos del Autor</label>
        <div>
          <input type="text" name="apellidos" id="apellidos">
        </div>
      </div>
      <div>
        <label for="id">Nuevo ID del Autor</label>
        <div>
          <input type="text" name="id" id="id">
        </div>
      </div>
      <div class="button-container mb-4">
        <input type="submit" value="Editar" class="edit-button" onclick="return validateForm()">
      </div>
    </form>
    <div id="error-message" class="error-message"></div>
    <div class="button-container mb-4">
      <form action="autor.jsp">
        <button type="submit">Atrás</button>
      </form>
    </div>
  </div>
</div>
</body>
</html>