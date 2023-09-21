<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Producto</title>

    <link rel="icon" href="assets/img/logo.png">
    <link rel="stylesheet" href="assets/css/registProduct.css">
</head>
<body>
    <h1>Registrar producto</h1>

    <br> <br>

    <form action="product" method="POST" enctype="multipart/form-data">
        <label>Nombre: </label>
        <input type="text" name="nameNewProduct">

        <br> 
    
        <label>Imagen: </label>
        <input type="file" name="photoNewProduct">

        <br> 

        <label>Disponibilidad [En libras]:</label>
        <input type="number" name="stockNewProduct">

        <br> 

        <label>Detalles:</label>
        <textarea name="detailsNewProduct" cols="30" rows="10"></textarea>

        <br> 

        <label>Precio [Por libra]: </label>
        <input type="number" name="priceNewProduct">

        <br> 

        <label>Tipo de empaque: </label>
        <select name="packageNewProduct">
            <option value="1">Caja</option>
            <option value="2">Canasta</option>
            <option value="3">Bolsa</option>
            <option value="4">Botella</option>
            <option value="5">Canastilla</option>
        </select>

        <br> 

        <label>Estado: </label>
        <select name="statusNewProduct">
            <option value="Disponible">Disponible</option>
            <option value="Suspendido">Suspendido</option>
        </select>

        <br> <br>

        <input type="submit" name="productForm" value="Registrar Producto">
    </form>
    
</body>
</html>