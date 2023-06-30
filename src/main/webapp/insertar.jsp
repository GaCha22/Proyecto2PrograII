<%--
  Created by IntelliJ IDEA.
  User: Aarón
  Date: 30/6/2023
  Time: 01:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Insertar Libro</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      background-color: #f2f2f2;
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
      background-color: #4caf50;
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
  </style>
</head>
<body>
<div class="container">
  <div class="form-group">
    <input type="text" placeholder="Signatura">
  </div>

  <div class="form-group">
    <input type="text" placeholder="ISBM">
  </div>

  <div class="form-group">
    <input type="text" placeholder="Título">
  </div>

  <div class="form-group">
    <input type="text" placeholder="Año">
  </div>

  <div class="form-group">
    <input type="text" placeholder="Ciudad">
  </div>

  <div class="form-group">
    <select>
      <option value="editorial1">Editorial 1</option>
      <option value="editorial2">Editorial 2</option>
      <option value="editorial3">Editorial 3</option>
      <option value="editorial4">Editorial 4</option>
    </select>
  </div>

  <div class="form-group">
    <select>
      <option value="tematica1">Temática 1</option>
      <option value="tematica2">Temática 2</option>
      <option value="tematica3">Temática 3</option>
      <option value="tematica4">Temática 4</option>
    </select>
  </div>

  <div class="form-group">
    <select>
      <option value="cantidad1">1</option>
      <option value="cantidad2">2</option>
      <option value="cantidad3">3</option>
      <option value="cantidad4">4</option>
    </select>
  </div>

  <form action="autor.jsp">
    <button type="submit">Agregar Autor</button>
  </form>

  <form action="editorial.jsp">
    <button type="submit">Agregar Editorial</button>
  </form>

  <div class="button-container">
    <form action="index.jsp">
      <button type="submit">Atrás</button>
    </form>

    <form action="index.jsp">
      <button type="submit">Guardar</button>
    </form>
  </div>

</div>
</body>
</html>
