<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eliminar Libro</title>
    <link rel="stylesheet" type="text/css" href="estilox.css/eliminar.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #435164;
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
            background-color: #ad3d46;
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

    <div class="input-container">

        <input type="text" placeholder="Buscar">
        <button type="submit">Buscar</button>

        <div class="row mt-4">
            <div class="col-sm-4 border text-center">
                <h3>Libro</h3>
            </div>
            <div class="col-sm-4 border text-center">
                <h3>Autor</h3>
            </div>
            <div class="col-sm-4 border text-center">
                <h3 style="white-space: nowrap;">Tematica</h3>

            </div>
    </div>
</body>
</html>

















