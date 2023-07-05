<%@ page import="cr.ac.ucr.ie.prograii.model.Autor" %>
<%@ page import="cr.ac.ucr.ie.prograii.service.AutorDAO" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Eliminar Autor</title>
    <link rel="stylesheet" type="text/css" href="../estilox.css/editar.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
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
            background-color: #3dad8d;
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
            background-color: #3dad8d;
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
        #editorial-label {
            text-align: center;
            /* Si quieres centrar el label respecto a su contenedor, puedes usar estas propiedades */
            display: block;
            margin: 0 auto;
        }

    </style>
    <script>

        $(document).ready(function() {
            var autocompleteEdit = $("#editorial");
            if (autocompleteEdit.data("autocomplete")) {
                autocompleteEdit.autocomplete({
                    source: function(request, response) {
                        $.ajax({
                            url: "/prograii/autocomplete/autocompleteEditorial",
                            type: "POST",
                            dataType: "xml",
                            data: {
                                term: request.term
                            },
                            success: function(data) {
                                var autocompleteData = [];
                                $("#codEditorial").val("");
                                $(data).find("editorial").each(function() {
                                    var objeto = $(this);
                                    var nombre = objeto.attr("nombre");
                                    var id = objeto.attr("idEditorial");

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

                        autocompleteEdit.val(selectedNombre); // Establecer el valor del campo de entrada con el nombre seleccionado
                        $("#codEditorial").val(selectedId);

                        return false;
                    }
                });
            }
        });

        function validateForm() {
            var nombre = document.getElementById("autor").value;
            var errorMessage = "";
            var codAutor = document.getElementById("codAutor").value;

            if (codAutor === ""){
                errorMessage = "El autor digitado no existe.";
            }

            if (nombre === "") {
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
    <h1>Eliminar Editorial</h1>
    <div class="button-container">
        <form action="/prograii/eliminarEditorial" method="post" onsubmit="return validateForm()">
            <div>
                <label for="editorial" id="editorial-label">Nombre del Editorial</label>
                <div>
                    <input type="text" placeholder="Editorial" name="editorial" id="editorial" data-autocomplete="true" autocomplete="off" class="ui-autocomplete-input">
                    <input type="hidden" name="codEditorial" id="codEditorial">
                </div>
            </div>
            <div class="button-container mb-4">
                <input type="submit" value="Eliminar" class="delete-button">
            </div>
        </form>
        <div id="error-message" class="error-message"></div>
        <div class="button-container mb-4">
            <form action="editorial.jsp">
                <button type="submit">Atrás</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>