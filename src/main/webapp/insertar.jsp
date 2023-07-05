<%@ page import="java.util.List" %>
<%@ page import="cr.ac.ucr.ie.prograii.model.Tematica" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Insertar Libro</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
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

    .form-group {
      margin-bottom: 10px;
    }

    select, input, button {
      font-size: 20px;
      padding: 10px;
    }

    .button-container button {
      background-color: #553dad;
      color: #fff;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      padding: 12px 20px;
      font-size: 18px;

    }

    .button-container {
      display: flex;
      justify-content: space-between;
      width: 100%;
    }

    .form-group button{
      background-color: #553dad;
      color: #fff;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      padding: 12px 20px;
      font-size: 18px;
      margin-top: 10px;
    }

    .button-container button:first-child {
      margin-right: 10px;
    }

    .author-group:not(:last-child) {
      margin-bottom: 10px;
    }

    body {
      background-image: url('giphy.gif');
      background-repeat: no-repeat;
      background-size: cover;
    }

    h1 {
      color: #fff;
    }

  </style>
  <script>
    $(document).ready(function() {
      var autocompleteTematica = $("#tematica");
      var autocompleteAutor = $("#autor");
      var autocompleteEditorial = $("#editorial");

      if (autocompleteTematica.data("autocomplete")) {
        autocompleteTematica.autocomplete({
          source: function(request, response) {
            $.ajax({
              url: "autocompleteTematica",
              type: "POST",
              dataType: "xml",
              data: {
                term: request.term
              },
              success: function(data) {
                var autocompleteData = [];

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

            autocompleteTematica.val(selectedNombre); // Establecer el valor del campo de entrada con el nombre seleccionado

            return false;
          }
        });
      }

      if (autocompleteAutor.data("autocomplete")) {
        autocompleteAutor.autocomplete({
          source: function(request, response) {
            $.ajax({
              url: "autocompleteAutor",
              type: "POST",
              dataType: "xml",
              data: {
                term: request.term
              },
              success: function(data) {
                var autocompleteData = [];

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

            autocompleteAutor.val(selectedNombre); // Establecer el valor del campo de entrada con el nombre seleccionado

            return false;
          }
        });
      }

      if (autocompleteEditorial.data("autocomplete")) {
        autocompleteEditorial.autocomplete({
          source: function(request, response) {
            $.ajax({
              url: "autocompleteEditorial",
              type: "POST",
              dataType: "xml",
              data: {
                term: request.term
              },
              success: function(data) {
                var autocompleteData = [];

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

            autocompleteEditorial.val(selectedNombre); // Establecer el valor del campo de entrada con el nombre seleccionado

            return false;
          }
        });
      }
    });

    var authorCount = 1;

    function addAuthorField() {
      var authorContainer = document.getElementById("author-container");

      var authorGroup = document.createElement("div");
      authorGroup.classList.add("author-group");

      var authorInput = document.createElement("input");
      authorInput.type = "text";
      authorInput.name = "autor" + authorCount;
      authorInput.placeholder = "Autor"

      var removeButton = document.createElement("button");
      removeButton.textContent = "Eliminar Autor";
      removeButton.type = "button";
      removeButton.classList.add("remove-author");

      removeButton.addEventListener("click", function() {
        authorGroup.parentNode.removeChild(authorGroup);
        showLastRemoveButton();
        authorCount--;
      });

      var removeGroup = document.createElement("div");
      removeGroup.appendChild(removeButton);
      authorGroup.appendChild(authorInput);
      authorGroup.appendChild(removeGroup);
      authorContainer.appendChild(authorGroup);

      authorCount++;
      showLastRemoveButton();
    }

    function showLastRemoveButton() {
      var removeButtons = document.getElementsByClassName("remove-author");
      for (var i = 0; i < removeButtons.length; i++) {
        var button = removeButtons[i];

        if (i === removeButtons.length - 1) {
          button.style.display = "inline-block";
        } else {
          button.style.display = "none";
        }
      }
    }
  </script>
</head>
<body>

<div class="container">
  <div class="form-group mb-4">
    <input type="text" placeholder="Signatura">
  </div>

  <div class="form-group mb-4">
    <input type="text" placeholder="ISBN">
  </div>

  <div class="form-group mb-4">
    <input type="text" placeholder="Título">
  </div>

  <div class="form-group mb-4">
    <input type="text" placeholder="Año">
  </div>

  <div class="form-group mb-4">
    <div id="author-container">
      <div class="author-group">
        <input type="text" placeholder="Autor" name="autor" id="autor" data-autocomplete="true" autocomplete="on">
      </div>
    </div>
      <button type="button" id="addAuthor-button" onclick="addAuthorField()">Agregar Autor</button>
    <div id="author-buttons"></div>
  </div>

  <div class="form-group mb-4">
    <input type="text" placeholder="Tematica" name="tematica" id="tematica" data-autocomplete="true" autocomplete="on">
  </div>

  <div class="form-group mb-4">
    <input type="text" placeholder="Editorial" name="editorial" id="editorial" data-autocomplete="true" autocomplete="on">
  </div>

  <div class="button-container mb-4">
    <form action="index.jsp">
      <button type="submit">Atrás</button>
    </form>

    <form action="index.jsp">
      <button type="submit">Guardar</button>
    </form>
  </div>

</div>
</body>
</html>
