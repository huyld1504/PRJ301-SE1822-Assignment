<%-- 
    Document   : CreateCar
    Created on : 14-03-2025, 01:07:51
    Author     : Thanh Vinh
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Car</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
    </head>
    <body class="bg-light">

        <c:if test="${sessionScope.SALE != null}">
            <jsp:useBean id="SALE" scope="session" class="models.SalesPerson"/>
            <!-- Top Navigation Bar -->
            <nav class="navbar navbar-expand-lg bg-info text-white p-3 shadow-lg">
                <div class="container-fluid d-flex justify-content-between align-items-center">
                    <h2 class="text-white fw-bold m-0">Welcome ${sessionScope.SALE.salesName}</h2>
                    <a href="MainServlet?action=sale-dashboard" class="btn btn-outline-light fw-bold">Back to Dashboard</a>
                </div>
            </nav>


            <div class="container mt-5">
                <h2 class="text-center text-success fw-bold mb-4">Create New Car</h2>

                <!-- Display Success/Error Message through session -->
                <c:if test="${sessionScope.MESSAGE != null}">
                    <div class="alert alert-success alert-dismissible fade show w-100" role="alert">
                        ${sessionScope.MESSAGE}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <!-- Remove the message after displaying it -->
                    <c:remove var="MESSAGE"/>
                </c:if>

                <c:if test="${sessionScope.ERROR != null}">
                    <div class="alert alert-danger alert-dismissible fade show w-100" role="alert">
                        ${sessionScope.ERROR}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <!-- Remove the error after displaying it -->
                    <c:remove var="ERROR"/>
                </c:if>

                <!-- Create Car Form -->
                <form action="MainServlet?action=create-car" method="post" class="row g-3 p-4 border rounded shadow-lg bg-light">
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Serial Number</label>
                        <input type="text" class="form-control" name="serialNumber" required />
                    </div>
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Model</label>
                        <input type="text" class="form-control" name="model" required />
                    </div>
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Colour</label>
                        <input type="text" class="form-control" name="colour" required />
                    </div>
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Year</label>
                        <input type="number" class="form-control" name="year" min="1500" max="<%= new java.util.Date().getYear() + 1900%>" required />
                    </div>
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Price</label>
                        <input type="number" step="0.1" class="form-control" name="price" required /> <!-- Thêm trường price -->
                    </div>
                    <div class="col-12 text-center">
                        <button type="submit" class="btn btn-success fw-bold px-5 py-2">Create Car</button>
                    </div>
                </form>
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



