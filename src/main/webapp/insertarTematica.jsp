<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insertar Tematica</title>
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
        }

        .form-group {
            margin-bottom: 10px;
        }

        select, input, button {
            font-size: 20px;
            padding: 10px;
        }

        .button-container button {
            background-color: #553dad;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            padding: 12px 20px;
            font-size: 18px;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            width: 100%;
        }

        .form-group button {
            background-color: #553dad;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            padding: 12px 20px;
            font-size: 18px;
            margin-top: 10px;
        }

        .button-container button:first-child {
            margin-right: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 style="color: #553dad;">Agregar nueva temática</h1>
    <form action="/prograii/agregarTematica" method="post">
        <div class="form-group mb-4">
            <label for="nombre" style="color: #333333;">Nombre Temática</label>
            <div>
                <input type="text" name="nombre" id="nombre">
            </div>
        </div>
        <div>
            <input type="submit" value="Agregar">
        </div>
    </form>
    <form action="tematica.jsp">
        <button type="submit">Atrás</button>
    </form>
</div>
</body>
</html>