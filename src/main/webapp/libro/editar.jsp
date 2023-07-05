<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Libro</title>
    <link rel="stylesheet" type="text/css" href="../estilox.css/editar.css">
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
            color: #fff;
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

    </style>
    <script>
        $(document).ready(function() {
            var autocompleteLibro = $("#search");
            var autocompleteTematica = $("#tematica");
            var autocompleteEditorial = $("#editorial");

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

                                $("#codLibro").val("");

                                $(data).find("libro").each(function() {
                                    var objeto = $(this);
                                    var titulo = objeto.attr("titulo");
                                    var id = objeto.attr("idLibro");
                                    var autor1 = objeto.attr("autor1");
                                    var codAutor1 = objeto.attr("codAutor1");
                                    var autor2 = objeto.attr("autor2");
                                    var codAutor2 = objeto.attr("codAutor2");
                                    var autor3 = objeto.attr("autor3");
                                    var codAutor3 = objeto.attr("codAutor3");
                                    var editorial = objeto.attr("editorial");
                                    var codEditorial = objeto.attr("codEditorial");
                                    var tematica = objeto.attr("tematica");
                                    var codTematica = objeto.attr("codTematica");
                                    var isbn = objeto.attr("isbn");

                                    var autocompleteItem = {
                                        label: titulo,
                                        value: {
                                            id: id,
                                            nombre: titulo,
                                            isbn: isbn,
                                            autor: autor1,
                                            codAutor: codAutor1,
                                            autor1: autor2,
                                            codAutor1: codAutor2,
                                            autor2: autor3,
                                            codAutor2: codAutor3,
                                            editorial: editorial,
                                            codEditorial: codEditorial,
                                            tematica: tematica,
                                            codTematica: codTematica
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
                        var selectedISBN = selectedObj.isbn;

                        var selectedAutor = selectedObj.autor;
                        var selectedCodAutor = selectedObj.codAutor;

                        var selectedAutor1 = selectedObj.autor1;
                        var selectedCodAutor1 = selectedObj.codAutor1;

                        var selectedAutor2 = selectedObj.autor2;
                        var selectedCodAutor2 = selectedObj.codAutor2;

                        var selectedEditorial = selectedObj.editorial;
                        var selectedCodEditorial = selectedObj.codEditorial;

                        var selectedTematica = selectedObj.tematica;
                        var selectedCodTematica = selectedObj.codTematica;

                        autocompleteLibro.val(selectedNombre);
                        $("#codLibro").val(selectedId);
                        $("#titulo").val(selectedNombre);
                        $("#isbn").val(selectedISBN);
                        $("#autor").val(selectedAutor);
                        $("#codAutor").val(selectedCodAutor);

                        if (selectedAutor1 !== "-1"){
                            $("#autor1").val(selectedAutor1);
                            $("#codAutor1").val(selectedCodAutor1);
                        }

                        if (selectedAutor2 !== "-1"){
                            $("#autor2").val(selectedAutor2);
                            $("#codAutor2").val(selectedCodAutor2);
                        }

                        $("#editorial").val(selectedEditorial);
                        $("#codEditorial").val(selectedCodEditorial);

                        $("#tematica").val(selectedTematica);
                        $("#codTematica").val(selectedCodTematica);

                        return false;
                    }
                });
            }

            autocompleteAutor($("#autor"), $("#codAutor"));
            autocompleteAutor($("#autor1"), $("#codAutor1"));
            autocompleteAutor($("#autor2"), $("#codAutor2"));

            if (autocompleteTematica.data("autocomplete")) {
                autocompleteTematica.autocomplete({
                    source: function(request, response) {
                        $.ajax({
                            url: "/prograii/autocomplete/autocompleteTematica",
                            type: "POST",
                            dataType: "xml",
                            data: {
                                term: request.term
                            },
                            success: function(data) {
                                var autocompleteData = [];

                                $("#codTematica").val("");

                                $(data).find("tematica").each(function() {
                                    var objeto = $(this);
                                    var nombre = objeto.attr("nombre");
                                    var id = objeto.attr("idTipo");

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

                        autocompleteTematica.val(selectedNombre); // Establecer el valor del campo de entrada con el nombre seleccionado
                        $("#codTematica").val(selectedId);

                        return false;
                    }
                });
            }

            if (autocompleteEditorial.data("autocomplete")) {
                autocompleteEditorial.autocomplete({
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

                        autocompleteEditorial.val(selectedNombre); // Establecer el valor del campo de entrada con el nombre seleccionado
                        $("#codEditorial").val(selectedId);

                        return false;
                    }
                });
            }
        });

        function autocompleteAutor(field, codField){
            if (field.data("autocomplete")) {
                field.autocomplete({
                    source: function(request, response) {
                        $.ajax({
                            url: "/prograii/autocomplete/autocompleteAutor",
                            type: "POST",
                            dataType: "xml",
                            data: {
                                term: request.term
                            },
                            success: function(data) {
                                var autocompleteData = [];

                                codField.val("");

                                $(data).find("autor").each(function() {
                                    var objeto = $(this);
                                    var nombre = objeto.attr("nombre");
                                    var id = objeto.attr("idAutor");

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

                        field.val(selectedNombre); // Establecer el valor del campo de entrada con el nombre seleccionado
                        codField.val(selectedId);
                        return false;
                    }
                });
            }
        }

        function goBack(){
            window.location.href = "./libro.jsp";
        }

        function validateForm() {
            var titulo = document.getElementById("titulo").value;
            var autor = document.getElementById("autor").value;
            var id = document.getElementById("codLibro").value;
            var tematica = document.getElementById("tematica").value;
            var editorial = document.getElementById("editorial").value;
            var codAutor = document.getElementById("codAutor").value;
            var codTematica = document.getElementById("codTematica").value;
            var codEditorial = document.getElementById("codEditorial").value;
            var isbn = document.getElementById("isbn").value;

            var errorMessage = "";

            if (titulo === "" ||  id === "" || editorial === "" || tematica === "" || isbn === "") {
                errorMessage = "Por favor, complete todos los campos.";
            }else if (autor === "" ){
                errorMessage = "No puedes dejar el primer autor en blanco";
            } else if (!/^\d+$/.test(id)) {
                errorMessage = "El id del libro debe contener solo números.";
            }else if (codAutor === ""){
                errorMessage = "El autor no existe, elija una de las opciones"
            } else if (codTematica === ""){
                errorMessage = "La tematica no existe, elija una de las opciones"
            } else if (codEditorial === ""){
                errorMessage = "La editorial no existe, elija una de las opciones"
            } else if (id === ""){
                errorMessage = "El libro a editar no existe, elija una de las opciones"
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

    <form action="./editarServlet" method="post" onsubmit="return validateForm()">
        <div>
            <label for="search">Buscar Libro</label>
            <div class="form-group mb-4">
                <input type="text" placeholder="Buscar" name="search" id="search" data-autocomplete="true" autocomplete="off" class="ui-autocomplete-input">
                <input type="hidden" name="codLibro" id="codLibro">
            </div>
        </div>

        <div>
            <label for="isbn">ISBN</label>
            <div class="form-group mb-4">
                <input type="text" placeholder="ISBN" id="isbn" name="isbn">
            </div>
        </div>

        <div>
            <label for="titulo">Titulo</label>
            <div class="form-group mb-4">
                <input type="text" placeholder="Título" name="titulo" id="titulo">
            </div>
        </div>

        <div>
            <label for="autor">Autor</label>
            <div class="form-group mb-4">
                <input type="text" placeholder="Autor" name="autor" id="autor" data-autocomplete="true" autocomplete="off" class="ui-autocomplete-input">
                <input type="hidden" name="codAutor" id="codAutor">
            </div>
        </div>

        <div>
            <label for="autor1">Autor 2</label>
            <div class="form-group mb-4">
                <input type="text" placeholder="Autor" name="autor1" id="autor1" data-autocomplete="true" autocomplete="off" class="ui-autocomplete-input">
                <input type="hidden" name="codAutor1" id="codAutor1">
            </div>
        </div>

        <div>
            <label for="autor2">Autor 3</label>
            <div class="form-group mb-4">
                <input type="text" placeholder="Autor" name="autor2" id="autor2" data-autocomplete="true" autocomplete="off" class="ui-autocomplete-input">
                <input type="hidden" name="codAutor2" id="codAutor2">
            </div>
        </div>

        <div>
            <label for="tematica">Tematica</label>
            <div class="form-group mb-4">
                <input type="text" placeholder="Tematica" name="tematica" id="tematica" data-autocomplete="true" autocomplete="off" class="ui-autocomplete-input">
                <input type="hidden" name="codTematica" id="codTematica">
            </div>
        </div>

        <div>
            <label for="editorial">Editorial</label>
            <div class="form-group mb-4">
                <input type="text" placeholder="Editorial" name="editorial" id="editorial" data-autocomplete="true" autocomplete="off" class="ui-autocomplete-input">
                <input type="hidden" name="codEditorial" id="codEditorial">
            </div>
        </div>

        <div id="error-message" class="error-message"></div>

        <div class="button-container mb-4">
            <button type="button" onclick="goBack()">Atrás</button>

                <button type="submit">Guardar</button>
        </div>
    </form>

</div>
</body>
</html>
