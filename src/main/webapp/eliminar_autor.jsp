<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Eliminar Autor</title>
  <link rel="stylesheet" type="text/css" href="estilox.css/inicio.css">
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

    .button-container .delete-button {
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

  </style>
</head>
<body>
<div class="container">
  <h1>Eliminar Autor</h1>
  <div class="button-container">
    <form action="/prograii/eliminar_autor" method="post">
      <div>
        <label for="nombre">Nombre del Autor</label>
        <div>
          <input type="text" name="nombre" id="nombre">
        </div>
      </div>
      <div class="button-container mb-4">
        <input type="submit" value="Eliminar" class="delete-button">
      </div>
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
