<%-- 
    Document   : CreateCustomer
    Created on : 09-03-2025, 23:09:09
    Author     : Thanh Vinh
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Customer</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
</head>
<body class="bg-light">

    <!-- Top Navigation Bar -->
    <nav class="navbar navbar-expand-lg bg-info text-white p-3 shadow-lg">
        <div class="container-fluid d-flex justify-content-between align-items-center">
            <h2 class="text-white fw-bold m-0">Welcome ${sessionScope.SALE.salesName}</h2>
            <a href="MainServlet?action=sale-dashboard" class="btn btn-outline-light fw-bold">Back to Dashboard</a>
        </div>
    </nav>

    <!-- Form tạo khách hàng -->
    <div class="container mt-5">
        <h2 class="text-center text-success fw-bold">Create Customer</h2>

        <c:if test="${not empty SUCCESS}">
            <div class="alert alert-success">${SUCCESS}</div>
        </c:if>

        <c:if test="${not empty ERROR}">
            <div class="alert alert-danger">${ERROR}</div>
        </c:if>

            <form action="MainServlet?action=create-customer" accept-charset="UTF-8" method="post" class="row g-3 p-4 border rounded shadow-lg bg-light">
            <div class="col-md-6">
                <label class="form-label fw-bold">Customer Name</label>
                <input type="text" class="form-control" name="custName" required />
            </div>
            <div class="col-md-6">
                <label class="form-label fw-bold">Phone</label>
                <input type="text" class="form-control" name="phone" required />
            </div>
            <div class="col-md-6">
                <label class="form-label fw-bold">Sex</label>
                <select class="form-select" name="sex" required>
                    <option value="M">Male</option>
                    <option value="F">Female</option>
                </select>
            </div>
            <div class="col-md-6">
                <label class="form-label fw-bold">Address</label>
                <input type="text" class="form-control" name="cusAddress" required />
            </div>
            <div class="col-12 text-center">
                <button type="submit" class="btn btn-success fw-bold px-5 py-2">Create Customer</button>
            </div>
        </form>

    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


