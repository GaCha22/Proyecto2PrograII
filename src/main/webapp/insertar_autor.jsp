<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Insertar Autor</title>
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
            text-align: center;
        }

        .button-container button {
            margin-bottom: 20px;
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
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Insertar Autor</h1>
    <div class="button-container">
        <form action="/prograii/insertar_autor" method="post">
            <div>
                <label for="nombre">Nombre del Autor</label>
                <div>
                    <input type="text" name="nombre" id="nombre">
                </div>
            </div>
            <div>
                <label for="apellidos">Apellidos del Autor</label>
                <div>
                    <input type="text" name="apellidos" id="apellidos">
                </div>
            </div>
            <div>
                <label for="id">ID del Autor</label>
                <div>
                    <input type="text" name="id" id="id">
                </div>
            </div>
            <div>
                <label for="nacionalidad">Nacionalidad del Autor</label>
                <div>
                    <input type="text" name="nacionalidad" id="nacionalidad">
                </div>
            </div>
            <div>
                <input type="submit" value="Guardar" class="save-button">
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