<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="assets/img/logo.png">
    <title>Lista de productos</title>

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
            <a href="product?navProduct=listProducts" class="buttonNav b2_1 center">Actualizar Lista</a>

            <a href="product?navProduct=registProduct" class="buttonNav b2_2">Registrar Producto</a>

            <form action="product" method="post" class="formNav2">
                <label class="label1">Buscar producto por...</label>
                <select name="searchFor" class="label1">
                    <option value="idProducto">ID</option>
                    <option value="nombreProducto">Nombre</option>
                    <option value="precioProducto">Precio</option>
                    <option value="disponibilidadPoducto">Disponibilidad</option>
                    <option value="idFKEmpaque">Empaque</option>
                    <option value="suspendidoProducto">Estado</option>
                </select>
        
                <input type="text" name="searchData" class="input2">
        
                <button type="submit" name="productForm" value="searchProductFor" class="buttonNav b2_3">Buscar</button>
            </form>
        </div>


        <div class="title1"><h1>Productos</h1></div>

        <hr class="hr1">


        <table class="table1 center">
            <tr>
                <td class="tableTitle">ID</td>
                <td class="tableTitle">Imagen</td>
                <td class="tableTitle">Nombre</td>
                <td class="tableTitle">Precio</td>
                <td class="tableTitle">Disponibilidad</td>
                <td class="tableTitle">Estado</td>
                <td class="tableTitle">Acciones</td>
            </tr>

            <c:forEach var="prod" items="${productList}">
                <tr>
                    <td class="tableContent">${prod.idProduct}</td>
                    <td class="tableContent"><img src="productimg?id=${prod.idProduct}" width=100px height=100px></td>
                    <td class="tableContent">${prod.nameProduct}</td>
                    <td class="tableContent">${prod.priceProduct}</td>
                    <td class="tableContent">${prod.availabilityProduct}</td>
                    <td class="tableContent">
                        <c:choose>
                            <c:when test="${prod.statusProduct eq 'Disponible'}">
    
                                    <form action="product" method="post">
                                        <input type="hidden" name="defineStatusProduct" value="${prod.idProduct}">
                                        <input type="hidden" name="defineStatus" value="Suspendido">
                                        <button class="stateButtonOff" type="submit" name="productForm" value="inactivateProduct">Suspender</button>
                                    </form>
    
                            </c:when>
                            <c:otherwise>
    
                                    <form action="product" method="post">
                                        <input type="hidden" name="defineStatusProduct" value="${prod.idProduct}">
                                        <input type="hidden" name="defineStatus" value="Disponible">
                                        <button class="stateButtonOn" type="submit" name="productForm" value="activateProduct">Habilitar</button>
                                    </form>
    
                            </c:otherwise>
                        </c:choose>
                    </td>
    
                    <td class="tableContent">
                        <form action="product" method="post">
                            <input type="hidden" name="searchProductToEdit" value="${prod.idProduct}">
                            <button class="editButton" type="submit" name="productForm" value="searchOneProduct">Editar producto</button>
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