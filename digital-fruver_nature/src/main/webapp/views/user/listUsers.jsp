<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="assets/img/logo.png">
    <title>Lista de usuarios</title>

    <link rel="stylesheet" href="assets/css/list.css">
    <script src="https://kit.fontawesome.com/6131ecdde6.js" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container">
        <nav class="navbar1">
            <div class="profileIcon center"><i class="fa-solid fa-user"></i></div>
            <a href="user?navUser=indexAdmin" class="profileButton center">Inicio</a>
            <div class="textLogo"><h3>Digital-Fruver Nature</h3></div>
            <div class="logoImg"><img src="assets/img/logo.png" alt=""></div>
        </nav>

        <div class="navbar2">
            <a href="user?navUser=listUsers" class="buttonNav b2_1 center">Actualizar Lista</a>

            <button class="buttonNav b2_2">Registrar Usuario</button>

            <form action="user" method="post"  class="formNav2">
                <label class="label1">Buscar usuario por...</label>
                <select name="searchFor" class="label1">
                    <option value="idUsuario">ID</option>
                    <option value="prioridadUsuario">Relevancia</option>
                    <option value="nombreUsuario">Nombre</option>
                    <option value="apellidoUsuario">Apellido</option>
                    <option value="emailUsuario">E-mail</option>
                    <option value="telefonoUsuario">Telefono</option>
                    <option value="usernameUsuario">Usuario</option>
                    <option value="estadoUsuario">Estado</option>
                    <option value="tipoDocUsuario">Tipo de documento</option>
                    <option value="documentoUsuario">Número de documento</option>
                    <option value="idFKRol">Rol</option>
                </select>
        
                <input type="text" name="searchData" class="input2">
        
                <button type="submit" name="userForm" value="searchUserFor" class="buttonNav b2_3">Buscar</button>
            </form>
        </div>


        <div class="title1"><h1>Usuarios</h1></div>

        <hr class="hr1">


        <table class="table1 center">
            <tr>
                <td class="tableTitle">ID</td>
                <td class="tableTitle">Relevancia</td>
                <td class="tableTitle">Nombre</td>
                <td class="tableTitle">Apellido</td>
                <td class="tableTitle">E-mail</td>
                <td class="tableTitle">Telefono</td>
                <td class="tableTitle">Usuario</td>
                <td class="tableTitle">Definir estado</td>
                <td class="tableTitle">Acciones</td>
            </tr>
            <c:forEach var="usu" items="${user}">
                <tr>
                    <td class="tableContent">${usu.idUser}</td>
                    <td class="tableContent">${usu.priorityUser}</td>
                    <td class="tableContent">${usu.nameUser}</td>
                    <td class="tableContent">${usu.lastNameUser}</td>
                    <td class="tableContent">${usu.emailUser}</td>
                    <td class="tableContent">${usu.celphoneUser}</td>
                    <td class="tableContent">${usu.usernameUser}</td>
                    <td class="tableContent">
                        <c:choose>
                            <c:when test="${usu.stateUser eq 'Activo'}">
    
                                    <form action="user" method="post">
                                        <input type="hidden" name="defineStatusUser" value="${usu.idUser}">
                                        <input type="hidden" name="defineStatus" value="Inactivo">
                                        <button class="stateButtonOff" type="submit" name="userForm" value="inactivateUser">Inactivar</button>
                                    </form>
    
                            </c:when>
                            <c:otherwise>
    
                                    <form action="user" method="post">
                                        <input type="hidden" name="defineStatusUser" value="${usu.idUser}">
                                        <input type="hidden" name="defineStatus" value="Activo">
                                        <button class="stateButtonOn" type="submit" name="userForm" value="activateUser">Activar</button>
                                    </form>
    
                            </c:otherwise>
                        </c:choose>
                    </td>
    
                    <td class="tableContent">
                        <form action="user" method="post">
                            <input type="hidden" name="searchUserToEdit" value="${usu.idUser}">
                            <button class="editButton" type="submit" name="userForm" value="searchOneUser">Editar usuario</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <footer class="footer">
            <a class="buttonFoot center">Nosotros</a>
            <div class="footerTextLogo"><h3>Digital-Fruver Nature</h3></div>
            <div class="footerLogo"><img src="assets/img/logo.png"></div>
        </footer>
    </div>
</body>
</html>