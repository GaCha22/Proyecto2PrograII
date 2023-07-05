<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Biblioteca</title>
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
            background-color: #553dad;
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
        <form action="libro.jsp">
            <button type="submit">Libro</button>
        </form>
        <form action="autor/autor.jsp">
            <button type="submit">Autor</button>
        </form>
        <form action="editorial.jsp">
            <button type="submit">Editorial</button>
        </form>
        <form action="tematica.jsp">
            <button type="submit">Temática</button>
        </form>
    </div>
    <div>
    </div>
</div>
</body>
</html>