<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Libro</title>
    <link rel="stylesheet" type="text/css" href="estilox.css/editar.css">
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

    </style>
</head>
<body>

<div class="container">

    <div class="form-group mb-4">
        <input type="text" placeholder="Título">
    </div>

    <div class="form-group mb-4">
        <input type="text" placeholder="Año">
    </div>

    <div class="form-group mb-4">
        <select>
            <option value="editorial1">Editorial 1</option>
            <option value="editorial2">Editorial 2</option>
            <option value="editorial3">Editorial 3</option>
            <option value="editorial4">Editorial 4</option>
        </select>

        <button type="submit">Guardar</button>
    </div>

    <div class="form-group mb-4">
        <select>
            <option value="tematica1">Temática 1</option>
            <option value="tematica2">Temática 2</option>
            <option value="tematica3">Temática 3</option>
            <option value="tematica4">Temática 4</option>
        </select>
    </div>

    <div class="form-group mb-4">
        <select>
            <option value="Autores">Autor 1</option>
        </select>

        <button type="submit">Guardar</button>
    </div>

    <div class="form-group mb-4">
        <select>
            <option value="cantidad1">1</option>
            <option value="cantidad2">2</option>
            <option value="cantidad3">3</option>
            <option value="cantidad4">4</option>
        </select>
    </div>

    <div class="button-container mb-4">
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
