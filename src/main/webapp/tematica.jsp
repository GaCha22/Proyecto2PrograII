<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Menu Temática</title>
  <link rel="stylesheet" type="text/css" href="estilox.css/tematica.css">
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
      background-color: #3E164A;
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
  <div class="button-container">
    <form action="insertarTematica.jsp">
      <button type="submit">Agregar Temática</button>
    </form>
    <form action="editarTematica.jsp" method="post">
      <button type="submit">Editar Tématica</button>
    </form>
    <form action="eliminarTematica.jsp" method="post">
      <button type="submit">Eliminar Tématica</button>
    </form>
  </div>
</div>
</body>
</html>

