<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Autor</title>
</head>
<body>
<h1>Iniciar sesión</h1>
<form action="/prograii/insercionfinalizada" method="post">
    <div>
        <label for="nombre">Nombre del Autor</label>
        <div>
            <input type="text" name="nombre" id="nombre">
        </div>
    </div>
    <div>
        <label for="apellidos">Apellidos del Autor</label>
        <div>
            <input type="text" name="apellidos" id="apellidos">
        </div>
    </div>
    <div>
        <label for="id">ID del Autor</label>
        <div>
            <input type="text" name="id" id="id">
        </div>
    </div>
    <div>
        <label for="nacionalidad">Nacionalidad del Autor</label>
        <div>
            <input type="text" name="nacionalidad" id="nacionalidad">
        </div>
    </div>
    <div>
        <input type="submit" value="Guardar">
    </div>
</form>
<div class="button-container mb-4">
    <form action="autor.jsp">
        <button type="submit">Atrás</button>
    </form>
</div>
</body>
</html>