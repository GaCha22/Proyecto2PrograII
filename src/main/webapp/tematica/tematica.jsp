<%@ page import="cr.ac.ucr.ie.prograii.model.Tematica" %>
<%@ page import="cr.ac.ucr.ie.prograii.service.TematicaDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Menu Temática</title>
  <link rel="stylesheet" type="text/css" href="../estilox.css/tematica.css">
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
      margin-bottom: 20px;
      background-color: #3E164A;
      color: #fff;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      padding: 12px 20px;
      font-size: 18px;
    }

    table {
      font-size: 20px;
      color: #000000;
      border-collapse: collapse;
      width: 300px;
      margin: 50px;
      background-color: #fff; /* Fondo blanco */
    }

    th,
    td {
      padding: 10px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }

    th {
      background-color: #3E164A;
    }

    table a {
      color: #000000;
      text-decoration: none;
    }

  </style>
</head>
<body>
<div style="overflow: auto; max-height: 300px; width: 400px; margin: 50px;">
  <table class="table table-sm table-dark table-bordered table-hover">
    <thead>
    <tr>
      <th>ID</th>
      <th>Nombre</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Tematica> tematicas = TematicaDAO.abrirDocumento("tematicas.xml").getTematicas();
      for (Tematica tematica : tematicas) {
    %>
    <tr>
      <td><%= tematica.getIdTipo() %></td>
      <td><a href="editarTematica.jsp"><%=tematica.getNombreTematica()%></a></td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>
<div class="container">
  <div class="button-container">
    <form action="insertarTematica.jsp">
      <button type="submit">Agregar Temática</button>
    </form>
    <form action="editarTematica.jsp">
      <button type="submit">Editar Tématica</button>
    </form>
    <form action="eliminarTematica.jsp">
      <button type="submit">Eliminar Tématica</button>
    </form>
    <form action="../pagina_principal.jsp" method="post">
      <button type="submit">Atras</button>
    </form>
  </div>
</div>
</body>
</html>
