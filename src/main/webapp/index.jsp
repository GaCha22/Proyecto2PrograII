<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="estilox.css/inicio.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script><style type="text/css" id="operaUserStyle"></style>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
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

        .button-container .login-button {
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

        body {
            background-image: url('./giphy.gif');
            background-repeat: no-repeat;
            background-size: cover;
        }

        label {
            color: #fff;
        }

        h1 {
            color: #fff;
        }

        .error-message {
            color: #fff;
        }

    </style>
    <script>
        function validateForm() {
            var nombre = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var errorMessage = "";

            if (nombre === "" || password === "") {
                errorMessage = "Por favor, complete todos los campos.";
            }

            if (errorMessage !== "") {
                document.getElementById("error-message").innerText = errorMessage;
                return false;
            }

        }

    </script>
</head>
<body>
<div class="container">
    <h1>Iniciar sesión</h1>
    <form action="/prograii/login" method="post" onsubmit="return validateForm()">
        <div>
            <label for="username">Username</label>
            <div>
                <input type="text" name="username" id="username">
            </div>
        </div>
        <div>
            <label for="password">Password</label>
            <div>
                <input type="password" name="password" id="password">
            </div>
        </div>
        <div id="error-message" class="error-message"></div>
        <div class="button-container">
            <input type="submit" value="Login" class="login-button">
        </div>
    </form>
</div>
</body>
</html>