<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Editar Autor</title>
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
      display: flex;
      flex-direction: column;
      align-items: center;
      text-align: center;
    }

    .button-container button {
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

    .button-container .save-button {
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
      background-color: #553dad;
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
  <div class="search-bar">
    <input type="text" placeholder="Buscar autor" name="search" id="search">
    <button type="button">Buscar</button>
  </div>
  <div class="button-container">
    <form action="/prograii/editar_autor" method="post" onsubmit="return validateForm()">
      <div>
        <label for="nombre">Nombre del Autor</label>
        <div>
          <input type="text" placeholder="Nombre del Autor" name="nombre" id="nombre">
        </div>
      </div>
      <div>
        <label for="apellidos">Apellidos del Autor</label>
        <div>
          <input type="text" placeholder="Apellidos del Autor" name="apellidos" id="apellidos">
        </div>
      </div>
      <div>
        <label for="id">ID del Autor</label>
        <div>
          <input type="text" placeholder="ID del Autor" name="id" id="id">
        </div>
      </div>
      <div>
        <input type="submit" value="Editar" class="save-button">
      </div>
      <div id="error-message" class="error-message"></div>
    </form>
    <div class="button-container mb-4">
      <form action="autor.jsp">
        <button type="submit">Atrás</button>
      </form>
    </div>
  </div>
</div>
</body>
</html>
