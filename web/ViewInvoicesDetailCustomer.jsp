<%-- 
    Document   : ViewInvoicesDetailCustomer
    Created on : 18-03-2025, 21:22:16
    Author     : Thanh Vinh
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice Details</title>

        <!-- CSS Bootstrap link -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">

        <!-- Font awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>

        <!-- Custom CSS for red titles -->
        <style>
            .red-title {
                color: #e74c3c;  /* Light red color */
                font-weight: bold;  /* Not bold */
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.CUSTOMER != null}">
            <jsp:useBean scope="session" class="models.Customer" id="CUSTOMER"/>

            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid bg-info" style="height: 10vh;">
                    <a class="navbar-brand fs-2" href="#">Welcome ${CUSTOMER.custName}</a>
                    <a href="MainServlet?action=view-invoices-customer" class="btn btn-outline-light fw-bold">Back to View Invoices</a>
                </div>
            </nav>

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

            <div class="container mt-4">
                <h1 class="text-center text-primary mb-4">Invoice Details</h1>

                <!-- Customer Information -->
                <h4 class="red-title">Customer Information</h4>
                <table class="table table-bordered">
                    <tr><th>ID</th><td>${customer.custID}</td></tr>
                    <tr><th>Name</th><td>${customer.custName}</td></tr>
                    <tr><th>Phone</th><td>${customer.phone}</td></tr>
                    <tr><th>Address</th><td>${customer.cusAddress}</td></tr>
                </table>

                <!-- Car Information -->
                <h4 class="red-title">Car Information</h4>
                <table class="table table-bordered">
                    <tr><th>Car ID</th><td>${car.carID}</td></tr>
                    <tr><th>Serial Number</th><td>${car.serialNumber}</td></tr>
                    <tr><th>Model</th><td>${car.model}</td></tr>
                    <tr><th>Colour</th><td>${car.colour}</td></tr>
                    <tr><th>Year</th><td>${car.year}</td></tr>
                    <tr><th>Price</th><td>${car.price}</td></tr>
                </table>

                <!-- Salesperson Information -->
                <h4 class="red-title">Salesperson Information</h4>
                <table class="table table-bordered">
                    <tr><th>Sales ID</th><td>${salesPerson.salesID}</td></tr>
                    <tr><th>Name</th><td>${salesPerson.salesName}</td></tr>
                    <tr><th>Birthday</th><td>${salesPerson.birthday}</td></tr>
                    <tr><th>Address</th><td>${salesPerson.salesAddress}</td></tr>
                </table>
            </div>

        </c:if>

        <c:if test="${sessionScope.CUSTOMER == null}">
            <div class="alert alert-danger" role="alert">
                You don't have any permission to access this resource. Please <a href="MainServlet?action=home" class="alert-link fw-bold">login here</a>!
            </div>
        </c:if>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </body>
</html>


