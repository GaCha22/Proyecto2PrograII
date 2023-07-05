<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Insertar Libro</title>
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

    .author-container {
      padding-bottom: 10px;
    }


    body {
      background-image: url('../giphy.gif');
      background-repeat: no-repeat;
      background-size: cover;
    }

    .error-message {
      color: #fff;
      margin-top: 10px;
      align-items: center;
    }

  </style>
  <script>
    $(document).ready(function() {
      var autocompleteTematica = $("#tematica");
      var autocompleteAutor1 = $("#autor");
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
            var selectedId = selectedObj.id;

            autocompleteTematica.val(selectedNombre); // Establecer el valor del campo de entrada con el nombre seleccionado
            $("#codTematica").val(selectedId);

            return false;
          }
        });
      }

      autocompleteAutor(autocompleteAutor1, $("#codAutor"));

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
            var selectedId = selectedObj.id;

            field.val(selectedNombre); // Establecer el valor del campo de entrada con el nombre seleccionado
            codField.val(selectedId)
            return false;
          }
        });
      }
    }

    var authorCount = 1;

    function addAuthorField() {
      var authorContainer = document.getElementById("author-container");

      var authorGroup = document.createElement("div");
      authorGroup.classList.add("author-group");

      var authorInput = document.createElement("input");
      authorInput.type = "text";
      authorInput.name = "autor" + authorCount;
      // authorInput.id = "autor" + authorCount;
      authorInput.placeholder = "Autor";
      authorInput.autocomplete = "on";
      authorInput.setAttribute("data-autocomplete", "true");

      var codAuthorInput = document.createElement("input");
      codAuthorInput.type = "hidden";
      codAuthorInput.name = "codAutor" + authorCount;
      codAuthorInput.placeholder = "Autor code"

      var removeButton = document.createElement("button");
      removeButton.textContent = "Eliminar Autor";
      removeButton.type = "button";
      removeButton.classList.add("remove-author");

      removeButton.addEventListener("click", function() {
        authorGroup.parentNode.removeChild(authorGroup);
        showLastRemoveButton();
        authorCount--;
      });
      if (authorCount < 3) {
        var removeGroup = document.createElement("div");
        removeGroup.appendChild(removeButton);
        authorGroup.appendChild(authorInput);
        authorGroup.appendChild(codAuthorInput);
        authorGroup.appendChild(removeGroup);
        authorContainer.appendChild(authorGroup);
        authorCount++;
        showLastRemoveButton();
        autocompleteAutor($(authorInput), $(codAuthorInput))
      }
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

    function validateForm() {
      var titulo = document.getElementById("titulo").value;
      var autor = document.getElementById("autor").value;
      var id = document.getElementById("idLibro").value;
      var tematica = document.getElementById("tematica").value;
      var editorial = document.getElementById("editorial").value;
      var codAutor = document.getElementById("codAutor").value
      var codTematica = document.getElementById("codTematica").value
      var codEditorial = document.getElementById("codEditorial").value

      var errorMessage = "";

      if (titulo === "" || autor === "" || id === "" || editorial === "" || tematica === "") {
        errorMessage = "Por favor, complete todos los campos.";
      } else if (!/^\d+$/.test(id)) {
        errorMessage = "El id del libro debe contener solo números.";
      }else if (codAutor === ""){
        errorMessage = "El autor no existe, elija una de las opciones"
      } else if (codTematica === ""){
        errorMessage = "La tematica no existe, elija una de las opciones"
      } else if (codEditorial === ""){
        errorMessage = "La editorial no existe, elija una de las opciones"
      }

      if (errorMessage !== "") {
        document.getElementById("error-message").innerText = errorMessage;
        return false;
      }
    }

    function goBack(){
      window.location.href = "./libro.jsp";
    }
  </script>
<body>

<div class="container">
  <form action="./insertarLibro" method="post">

  <div class="form-group mb-4">
    <input type="text" placeholder="Id del Libro" id="idLibro" name="idLibro">
  </div>

  <div class="form-group mb-4">
    <input type="text" placeholder="Título" id="titulo" name="titulo">
  </div>

  <div class="form-group mb-4">
    <div id="author-container">
      <div class="author-group">
        <input type="text" placeholder="Autor" name="autor" id="autor" data-autocomplete="true" autocomplete="off" class="ui-autocomplete-input">
        <input type="hidden" name="codAutor" id="codAutor">
      </div>
    </div>
    <button type="button" id="addAuthor-button" onclick="addAuthorField()">Agregar Autor</button>
    <div id="author-buttons"></div>
  </div>

  <div class="form-group mb-4">
    <input type="text" placeholder="Tematica" name="tematica" id="tematica" data-autocomplete="true" autocomplete="off" class="ui-autocomplete-input">
    <input type="hidden" name="codTematica" id="codTematica">
  </div>

  <div class="form-group mb-4">
    <input type="text" placeholder="Editorial" name="editorial" id="editorial" data-autocomplete="true" autocomplete="off" class="ui-autocomplete-input">
    <input type="hidden" name="codEditorial" id="codEditorial">
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