<%-- 
    Document   : UpdateCar
    Created on : 14-03-2025, 01:55:42
    Author     : Thanh Vinh
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- chuyển định dạng số -->
<%@ page import="models.Car" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Car</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <c:if test="${sessionScope.SALE != null}">
            <jsp:useBean id="SALE" scope="session" class="models.SalesPerson"/>

            <nav class="navbar navbar-expand-lg bg-info text-white p-3 shadow-lg">
                <div class="container-fluid">
                    <h2 class="text-white fw-bold m-0">Edit Car</h2>
                    <a href="MainServlet?action=sale-dashboard" class="btn btn-outline-light fw-bold">Back to Home</a>
                </div>
            </nav>

            <div class="container py-5">
                <form action="MainServlet?action=up-edit-car" method="post">
                    <!-- Car ID -->
                    <input type="hidden" name="carID" value="${car.carID}" />

                    <!-- Serial Number -->
                    <div class="mb-3">
                        <label for="serialNumber" class="form-label">Serial Number</label>
                        <input type="text" class="form-control" name="serialNumber" value="${car.serialNumber}" required />
                    </div>

                    <!-- Model -->
                    <div class="mb-3">
                        <label for="model" class="form-label">Model</label>
                        <input type="text" class="form-control" name="model" value="${car.model}" required />
                    </div>

                    <!-- Colour -->
                    <div class="mb-3">
                        <label for="colour" class="form-label">Colour</label>
                        <input type="text" class="form-control" name="colour" value="${car.colour}" required />
                    </div>

                    <!-- Year -->
                    <div class="mb-3">
                        <label for="year" class="form-label">Year</label>
                        <input type="number" class="form-control" name="year" value="${car.year}" required />
                    </div>

                    <!-- Price -->
                    <div class="mb-3">
                        <label for="price" class="form-label">Price</label>
                        <input type="number" step="0.1" class="form-control" name="price" value="<fmt:formatNumber value="${car.price}" pattern="####" />" required />
                    </div>

                    <button type="submit" class="btn btn-info">Update</button>
                </form>
            </div>

            <!-- Hiển thị thông báo thành công hoặc lỗi -->
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

        </c:if>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>



