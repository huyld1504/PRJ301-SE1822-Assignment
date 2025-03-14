<%-- 
    Document   : ReadCar
    Created on : 14-03-2025, 00:47:42
    Author     : Thanh Vinh
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Car List</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
    </head>
    <body class="bg-light">

        <!-- Kiểm tra xem đã đăng nhập chưa -->
        <c:if test="${sessionScope.SALE != null}">
            <jsp:useBean id="SALE" scope="session" class="models.SalesPerson"/>

            <div class="container py-5">
                <h2 class="text-center text-primary fw-bold mb-4">Car List</h2>

                <!-- Display Success/Error Message -->
                <c:if test="${requestScope.MESSAGE != null}">
                    <div class="alert alert-success alert-dismissible fade show w-25 z-3 position-absolute" role="alert">
                        ${requestScope.MESSAGE}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <c:if test="${requestScope.ERROR != null}">
                    <div class="alert alert-danger alert-dismissible fade show w-25 z-3 position-absolute" role="alert">
                        ${requestScope.ERROR}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <!-- Bảng danh sách xe -->
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
                            <c:forEach var="car" items="${CAR_LIST}">
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


                <!-- Nút quay lại trang Dashboard -->
                <div class="text-center mt-4">
                    <a href="MainServlet?action=sale-dashboard" class="btn btn-secondary">
                        <i class="fa-solid fa-arrow-left"></i> Back to Dashboard
                    </a>
                </div>
            </div>

        </c:if>

        <c:if test="${sessionScope.SALE == null}">
            <div class="alert alert-danger" role="alert">
                You don't have permission to access this resource. Please <a href="MainServlet?action=login-sale-page" class="alert-link fw-bold">login here</a>!
            </div>
        </c:if>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>

