<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Editorial</title>
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
            background-color: #3dada0;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            padding: 12px 20px;
            font-size: 18px;
        }
        .custom-bg {
            background-color: #22272e; /* Reemplaza #FF0000 con el color deseado */
        }

        .custom-text {
            color: #FFFFFF; /* Reemplaza #FFFFFF con el color deseado para el texto */
        }

    </style>

</head>
    <body>



        <div class="container text-center">
            <div>
                <form action="insertarEditorial.jsp" method="POST">
                    <button type="submit" class="btn btn-success mb-5">Añadir</button>
                </form>
                <form action="editarEditorial.jsp" method="POST">
                    <button type="submit" class="btn btn-success mb-5">Editar</button>
                </form>
                <form action="eliminarEditorial.html" method="POST">
                    <button type="submit" class="btn btn-success mb-5">Eliminar</button>
                </form>
            </div>
            <div class="row">
                <div class="col border custom-bg custom-text">
                    Editorial
                </div>
                <div class="col border custom-bg custom-text">
                    ID
                </div>
            </div>
        </div>


</body>
</html>