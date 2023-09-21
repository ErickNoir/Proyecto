<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse</title>
</head>
<body>
    <h1>Registrarse</h1>

    <form action="user" method="post">
        <label>Nombre: </label>
        <input type="text" name="nameUser">
    
    <br>

        <label>Apellido: </label>
        <input type="text" name="lastnameUser">

    <br>

        <label>E-mail: </label>
        <input type="email" name="emailUser">

    <br>

        <label>Telefono: </label>
        <input type="number" name="phoneUser">
    
    <br>

        <label>Tipo de Documento: </label>
        <select name="typeDocUser">
            <option value="C.C.">Cédula de ciudadania</option>
            <option value="C.E.">Cédula de extrangería</option>
            <option value="P.B.">Pasaporte</option>
            <option value="R.C.">Registro civil</option>
        </select>
    
    <br>

        <label>Número de documento: </label>
        <input type="number" name="documentUser">

    <br>

        <label>Nombre de usuario: </label>
        <input type="text" name="userUser">

    <br>

        <label>Contraseña: </label>
        <input type="password" name="passwordUser">

    <br>

        <label>Tipo de usuario: </label>
        <select name="typeUser">
            <option value="3">Cliente</option>
            <option value="2">Empleado</option>
        </select>

    <br><br>

        <button type="submit" name="userForm" value="registNewUser">Registrarse</button>
    </form>

    <br><br>

    <p>Si ya tienes una cuenta <a href="user?navUser=login">inicia sesión</a></p>

    <br><br>

    <a href="user?navUser=login">Cancelar</a>
</body>
</html>