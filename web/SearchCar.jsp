<%-- 
    Document   : SearchCar
    Created on : 14-03-2025, 03:26:15
    Author     : Thanh Vinh
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Car</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <!-- Top Navigation Bar -->
        <nav class="navbar navbar-expand-lg bg-info text-white p-3 shadow-lg">
            <div class="container-fluid d-flex justify-content-between align-items-center">
                <h2 class="text-white fw-bold m-0">Search Car</h2>
                <a href="MainServlet?action=sale-dashboard" class="btn btn-outline-light fw-bold">Back to Dashboard</a>
            </div>
        </nav>

        <div class="container mt-5">
            <h3 class="text-center text-primary fw-bold mb-4">Search for Cars</h3>

            <!-- Search Form -->
            <form action="MainServlet?action=search-car" method="post" class="row g-3 p-4 border rounded shadow-lg bg-light">
                <div class="col-md-4">
                    <label for="serialNumber" class="form-label fw-bold">Serial Number</label>
                    <input type="text" class="form-control" name="serialNumber" placeholder="Enter serial number" />
                </div>
                <div class="col-md-4">
                    <label for="model" class="form-label fw-bold">Model</label>
                    <input type="text" class="form-control" name="model" placeholder="Enter model" />
                </div>
                <div class="col-md-4">
                    <label for="year" class="form-label fw-bold">Year</label>
                    <input type="number" class="form-control" name="year" placeholder="Enter year" />
                </div>
                <div class="col-12 text-center">
                    <button type="submit" class="btn btn-primary fw-bold px-5 py-2">Search</button>
                </div>
            </form>

            <!-- Display Results -->
            <c:if test="${not empty carList}">
                <h3 class="text-center text-primary fw-bold mb-4">Search Results</h3>
                <div class="table-responsive">
                    <table class="table table-hover table-bordered shadow-lg bg-white">
                        <thead class="table-info text-center">
                            <tr>
                                <th>Car ID</th>
                                <th>Serial Number</th>
                                <th>Model</th>
                                <th>Colour</th>
                                <th>Year</th>
                                <th>Price</th> 
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="car" items="${carList}">
                                <tr>
                                    <td class="text-center">${car.carID}</td>
                                    <td>${car.serialNumber}</td>
                                    <td class="text-center">${car.model}</td>
                                    <td class="text-center">${car.colour}</td>
                                    <td class="text-center">${car.year}</td>
                                    <td class="text-center">${car.price}</td> <!-- Hiển thị giá xe -->
                                    <td class="text-center">
                                        <a href="MainServlet?action=edit-car&id=${car.carID}" class="btn btn-warning btn-sm">
                                            <i class="fa-solid fa-edit"></i> Edit
                                        </a>
                                        <a href="MainServlet?action=delete-car&id=${car.carID}" 
                                           class="btn btn-danger btn-sm" 
                                           onclick="return confirm('Are you sure you want to delete this car?');">
                                            <i class="fa-solid fa-trash"></i> Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

