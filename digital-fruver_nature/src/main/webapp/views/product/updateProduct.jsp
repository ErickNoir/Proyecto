<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actualizar Producto</title>

    <link rel="icon" href="assets/img/logo.png">
    <link rel="stylesheet" href="assets/css/registProduct.css">
</head>
<body>
    <h1>Actualizar producto</h1>

    <br> <br>

    <c:forEach var="prod" items="${product}">
    <form action="product" method="POST" enctype="multipart/form-data">

        <input type="text" name="updateIDProduct" value="${prod.idProduct}" hidden>

        <label>Nombre: </label>
        <input type="text" name="updateNameProduct" value="${prod.nameProduct}">

        <br> 

        <img src="productimg?id=${prod.idProduct}" width=100px height=100px><br> 
    
        <label>Imagen: </label>
        <input type="file" name="updateImageProduct" value="productimg?id=${prod.idProduct}">

        <br> 

        <label>Disponibilidad [En libras]:</label>
        <input type="number" name="updateStockProduct" value="${prod.availabilityProduct}">

        <br> 

        <label>Detalles:</label>
        <textarea name="updateDetailsProduct" cols="30" rows="10">${prod.descriptionProduct}</textarea>

        <br> 

        <label>Precio [Por libra]: </label>
        <input type="number" name="updatePriceProduct" value="${prod.priceProduct}">

        <br> 

        <label>Tipo de empaque: </label>
        <c:choose>
            <c:when test="${prod.idFKpackaging eq '1'}">
                <select name="updatePackageProduct">
                    <option value="1">Caja</option>
                    <option value="2">Canasta</option>
                    <option value="3">Bolsa</option>
                    <option value="4">Botella</option>
                    <option value="5">Canastilla</option>
                </select>
            </c:when>

            <c:when test="${prod.idFKpackaging eq '2'}">
                <select name="updatePackageProduct">
                    <option value="2">Canasta</option>
                    <option value="1">Caja</option>
                    <option value="3">Bolsa</option>
                    <option value="4">Botella</option>
                    <option value="5">Canastilla</option>
                </select>
            </c:when>

            <c:when test="${prod.idFKpackaging eq '3'}">
                <select name="updatePackageProduct">
                    <option value="3">Bolsa</option>
                    <option value="2">Canasta</option>
                    <option value="1">Caja</option>
                    <option value="4">Botella</option>
                    <option value="5">Canastilla</option>
                </select>
            </c:when>

            <c:when test="${prod.idFKpackaging eq '4'}">
                <select name="updatePackageProduct">
                    <option value="4">Botella</option>
                    <option value="3">Bolsa</option>
                    <option value="2">Canasta</option>
                    <option value="1">Caja</option>     
                    <option value="5">Canastilla</option>
                </select>
            </c:when>

            <c:when test="${prod.idFKpackaging eq '5'}">
                <select name="updatePackageProduct">
                    <option value="5">Canastilla</option>
                    <option value="4">Botella</option>
                    <option value="3">Bolsa</option>
                    <option value="2">Canasta</option>
                    <option value="1">Caja</option>     
                </select>
            </c:when>
        </c:choose>

        <br> 

        <label>Estado: </label>
        <c:choose>
            <c:when test="${prod.statusProduct eq 'Disponible'}">
                <select name="updateStatusProduct">
                    <option value="Disponible">Disponible</option>
                    <option value="Suspendido">Suspendido</option>
                </select>
            </c:when>

            <c:when test="${prod.statusProduct eq 'Suspendido'}">
                <select name="updateStatusProduct">
                    <option value="Suspendido">Suspendido</option>
                    <option value="Disponible">Disponible</option>
                </select>
            </c:when>
        </c:choose>

        <br> <br>

        <input type="submit" name="productForm" value="Actualizar Producto">
    </form>
</c:forEach>
    
</body>
</html>