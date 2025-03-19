<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Invoice List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" 
              integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" 
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        .custom-navbar {
                background-color: darkblue; 
                border: 2px solid #0056b3; 
            }
    </style>
</head>
<body>
    <%
      
    %>
    <nav class="navbar navbar-expand-lg custom-navbar">
        <div class="container-fluid d-flex align-items-center">
            <a class="navbar-brand text-light" href="#">Invoice List</a>
            <span class="text-light me-3">Hello, ${sessionScope.saleName}!</span>
            <a class="nav-link text-light ms-auto" href="SaleDashboard.jsp">
                    <i class="fa-solid fa-house"></i> Home
                </a>
        </div>
    </nav>

    <div class="container mt-4">
        <h2 class="text-center">Invoice List</h2>

        <table class="table table-bordered table-hover mt-3">
            <thead class="table-dark">
                <tr>
                    <th>Invoice ID</th>
                    <th>Invoice Date</th>
                    <th>Sale ID</th>
                    <th>Car ID</th>
                    <th>Customer ID</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="invoice" items="${requestScope.LIST}">
                    <tr>
                        <td>${invoice.invoiceID}</td>
                        <td>${invoice.invoiceDate}</td>
                        <td>${invoice.salesID}</td>
                        <td>${invoice.carID}</td>
                        <td>${invoice.custID}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="text-center mt-4">
            <button class="btn btn-success" onclick="window.location.href='CreateInvoice.jsp'"><i class="fa-solid fa-plus"></i> Create New Invoice</button>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
