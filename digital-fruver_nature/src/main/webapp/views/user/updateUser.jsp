<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actualizar usuario</title>
</head>
<body>
    <h1>Actualizar usuario</h1>

    <c:forEach var="usu" items="${user}">
        <form action="user" method="post">
            <input type="hidden" name="updateIDUser" value="${usu.idUser}">

            <label>Nombre: </label>
            <input type="text" name="updateNameUser" value="${usu.nameUser}">
        
        <br>

            <label>Apellido: </label>
            <input type="text" name="updateLastnameUser" value="${usu.lastNameUser}">

        <br>

            <label>Relevancia: </label>
            <c:choose>
                <c:when test="${usu.priorityUser eq 'Baja'}">
                    <select name="updatePriorityUser">
                        <option value="Bajo">Baja</option>
                        <option value="Normal">Normal</option>
                        <option value="Alta">Alta</option>
                        <option value="Muy Alta">Muy alta</option>
                    </select>
                </c:when>

                <c:when test="${usu.priorityUser eq 'Normal'}">
                    <select name="updatePriorityUser">
                        <option value="Normal">Normal</option>
                        <option value="Bajo">Baja</option>
                        <option value="Alta">Alta</option>
                        <option value="Muy Alta">Muy alta</option>
                    </select>
                </c:when>

                <c:when test="${usu.priorityUser eq 'Alta'}">
                    <select name="upsatePriorityUser">
                        <option value="Alta">Alta</option>
                        <option value="Bajo">Baja</option>
                        <option value="Normal">Normal</option>
                        <option value="Muy Alta">Muy alta</option>
                    </select>
                </c:when>

                <c:when test="${usu.priorityUser eq 'Muy alta'}">
                    <select name="updatePriorityUser">
                        <option value="Muy Alta">Muy alta</option>
                        <option value="Alta">Alta</option>
                        <option value="Bajo">Baja</option>
                        <option value="Normal">Normal</option>
                    </select>
                </c:when>
            </c:choose>

        <br>

            <label>E-mail: </label>
            <input type="email" name="updateEmailUser" value="${usu.emailUser}">

        <br>

            <label>Telefono: </label>
            <input type="number" name="updatePhoneUser" value="${usu.celphoneUser}">
        
        <br>

        <label>Tipo de Documento: </label>
        <c:choose>
            <c:when test="${usu.typeDocumentUser eq 'C.C.'}">
                <select name="updateTypeDocUser">
                    <option value="C.C.">Cédula de ciudadania</option>
                    <option value="C.E.">Cédula de extrangería</option>
                    <option value="P.B.">Pasaporte</option>
                    <option value="R.C.">Registro civil</option>
                </select>
            </c:when>

            <c:when test="${usu.typeDocumentUser eq 'C.E.'}">
                <select name="updateTypeDocUser">
                    <option value="C.E.">Cédula de extrangería</option>
                    <option value="C.C.">Cédula de ciudadania</option>
                    <option value="P.B.">Pasaporte</option>
                    <option value="R.C.">Registro civil</option>
                </select>
            </c:when>

            <c:when test="${usu.typeDocumentUser eq 'P.B.'}">
                <select name="updateTypeDocUser">
                    <option value="P.B.">Pasaporte</option>
                    <option value="C.C.">Cédula de ciudadania</option>
                    <option value="C.E.">Cédula de extrangería</option>
                    <option value="R.C.">Registro civil</option>
                </select>
            </c:when>

            <c:when test="${usu.typeDocumentUser eq 'R.C.'}">
                <select name="updateTypeDocUser">
                    <option value="R.C.">Registro civil</option>
                    <option value="C.C.">Cédula de ciudadania</option>
                    <option value="C.E.">Cédula de extrangería</option>
                    <option value="P.B.">Pasaporte</option>
                </select>
            </c:when>
        </c:choose>

        <br>

            <label>Número de documento: </label>
            <input type="number" name="updateDocumentUser" value="${usu.documentNumberUser}">

        <br>

            <label>Nombre de usuario: </label>
            <input type="text" name="updateUserUser" value="${usu.usernameUser}">

        <br>

            <label>Contraseña: </label>
            <input type="password" name="updatePasswordUser" value="${usu.passwordUser}">

        <br>

            <label>Tipo de usuario: </label>
            <c:choose>
                <c:when test="${usu.idfkRolUser eq '1'}">
                    <select name="updateTypeUser">
                        <option value="3">Cliente</option>
                        <option value="2">Empleado</option>
                        <option value="1">Administrador</option>
                    </select>
                </c:when>

                <c:when test="${usu.idfkRolUser eq '2'}">
                    <select name="updateTypeUser">
                        <option value="2">Empleado</option>
                        <option value="3">Cliente</option>
                        <option value="1">Administrador</option>
                    </select>
                </c:when>

                <c:when test="${usu.idfkRolUser eq '3'}">
                    <select name="updateTypeUser">
                        <option value="3">Cliente</option>
                        <option value="2">Empleado</option>
                        <option value="1">Administrador</option>
                    </select>
                </c:when>
            </c:choose>

        <br>

            <label>Estado: </label>
            <c:choose>
                <c:when test="${usu.stateUser eq 'Activo'}">
                    <select name="updateStateUser">
                        <option value="Activo">Activo</option>
                        <option value="Inactivo">Inactivo</option>
                    </select>
                </c:when>

                <c:when test="${usu.stateUser eq 'Inactivo'}">
                    <select name="updateStateUser">
                        <option value="Inactivo">Inactivo</option>
                        <option value="Activo">Activo</option>
                    </select>
                </c:when>
            </c:choose>

        <br><br>

            <button type="submit" name="userForm" value="updateUser">Actualizar</button>
        </form>
    </c:forEach>

    <br><br>

    <a href="user?navUser=listUsers">Cancelar</a>
</body>
</html>