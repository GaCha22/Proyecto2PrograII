<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eliminar Libro</title>
    <link rel="stylesheet" type="text/css" href="../estilox.css/eliminar.css">
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
            justify-content: space-between;
            width: 100%;
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

        .button-container .delete-button {
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

        .error-message {
            color: #fff;
        }

    </style>
    <script>
        $(document).ready(function() {
            var autocompleteLibro = $("#search");

            if (autocompleteLibro.data("autocomplete")) {
                autocompleteLibro.autocomplete({
                    source: function(request, response) {
                        $.ajax({
                            url: "/prograii/autocomplete/autocompleteLibro",
                            type: "POST",
                            dataType: "xml",
                            data: {
                                term: request.term
                            },
                            success: function(data) {
                                var autocompleteData = [];

                                $(data).find("libro").each(function() {
                                    var objeto = $(this);
                                    var nombre = objeto.attr("titulo");
                                    var id = objeto.attr("idLibro");

                                    var autocompleteItem = {
                                        label: nombre,
                                        value: {
                                            id: id,
                                            nombre: nombre
                                        }
                                    };

                                    autocompleteData.push(autocompleteItem);
                                });

                                response(autocompleteData);
                            }
                        });
                    },
                    select: function(event, ui) {
                        var selectedObj = ui.item.value;
                        var selectedNombre = selectedObj.nombre;
                        var selectedId = selectedObj.id;

                        autocompleteLibro.val(selectedNombre);
                        $("#idLibro").val(selectedId);

                        return false;
                    }
                });
            }
        });

        function goBack(){
            window.location.href = "./buscarLibro";
        }

        function validateForm() {
            var titulo = document.getElementById("search").value;
            var errorMessage = "";
            var idLibro = document.getElementById("idLibro").value;

            if (titulo === "") {
                errorMessage = "Por favor, complete todos los campos.";
            }else if (idLibro === ""){
                errorMessage = "El autor digitado no existe.";
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
    <h1>Eliminar Libro</h1>
    <form action="./eliminarServlet" method="post" onsubmit="return validateForm()">
        <div>
            <label for="search">Titulo del Libro</label>
            <div>
                <input type="text" placeholder="Buscar" name="search" id="search" data-autocomplete="true" autocomplete="off" class="ui-autocomplete-input">
                <input type="hidden" name="idLibro" id="idLibro">
            </div>
        </div>
        <div id="error-message" class="error-message"></div>
        <div class="button-container mb-4">
            <button type="button" onclick="goBack()">Atrás</button>
            <button type="submit">Eliminar</button>
        </div>
    </form>
</div>
</body>
</html>