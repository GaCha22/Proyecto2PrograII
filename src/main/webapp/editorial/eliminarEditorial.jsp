<%--
  Created by IntelliJ IDEA.
  User: Luis
  Date: 04/07/2023
  Time: 04:24 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eliminar</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/css/bootstrap.min.css">

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

        .error-message {
            color: #fff;
            margin-top: 10px;
        }

    </style>

</head>
<body>
    <div>
        <div>
            <form action="/prograii_war_exploded/eliminarEditorial" method="post">
                <input type="text" name="id" placeholder="Buscar por id...">
                <input type="submit" value="Buscar">
            </form>
        </div>

    </div>

</body>
</html>
