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
    <script>
        $(document).ready(function() {
            var autocompleteLibro = $("#search");

            if (autocompleteLibro.data("autocomplete")) {
                autocompleteLibro.autocomplete({
                    source: function(request, response) {
                        $.ajax({
                            url: "autocompleteLibro",
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

                        autocompleteLibro.val(selectedNombre); // Establecer el valor del campo de entrada con el nombre seleccionado

                        return false;
                    }
                });
            }
        });

    </script>
</head>
<body>



<div class="container">

    <div class="input-container">

        <input type="text" placeholder="Buscar" name="search" id="search" data-autocomplete="true" autocomplete="off" class="ui-autocomplete-input">
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
    </div>
</div>
</body>
</html>