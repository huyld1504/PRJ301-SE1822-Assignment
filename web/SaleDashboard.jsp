<%-- 
    Document   : SaleDashboard.jsp
    Created on : 04-03-2025, 16:23:22
    Author     : Thanh Vinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("SALE") == null) {
        request.setAttribute("ERROR", "Truy cập không được cho phép!");
        request.getRequestDispatcher("MainServlet?action=login-sale-page").forward(request, response);
        return;
    }
%>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sale Dashboard</title>

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
            <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-primary" type="submit">Search</button>
            </form>
        </div>
    </nav>

    <!-- Dashboard Content -->
    <div class="container py-4">
        <!-- Row 1: Customer - Car - Settings -->
        <div class="row justify-content-center g-4">
            
            <!-- Customer Dropdown -->
            <div class="col-lg-4 col-md-6">
                <div class="dropdown">
                    <button class="btn btn-info w-100 py-4 fs-3 fw-bold text-white shadow-lg rounded dropdown-toggle" data-bs-toggle="dropdown">
                        <i class="fa-solid fa-users"></i> Customer
                    </button>
                    <ul class="dropdown-menu w-100">
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=create-customer"><i class="fa-solid fa-plus"></i> Create</a></li>
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=read-customer"><i class="fa-solid fa-eye"></i> Read</a></li>
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=update-customer"><i class="fa-solid fa-pen"></i> Update</a></li>
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=delete-customer"><i class="fa-solid fa-trash"></i> Delete</a></li>
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=search-customer"><i class="fa-solid fa-search"></i> Search by Name</a></li>
                    </ul>
                </div>
            </div>

            <!-- Car Dropdown -->
            <div class="col-lg-4 col-md-6">
                <div class="dropdown">
                    <button class="btn btn-info w-100 py-4 fs-3 fw-bold text-white shadow-lg rounded dropdown-toggle" data-bs-toggle="dropdown">
                        <i class="fa-solid fa-car"></i> Car
                    </button>
                    <ul class="dropdown-menu w-100">
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=create-car"><i class="fa-solid fa-plus"></i> Create</a></li>
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=read-car"><i class="fa-solid fa-eye"></i> Read</a></li>
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=update-car"><i class="fa-solid fa-pen"></i> Update</a></li>
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=delete-car"><i class="fa-solid fa-trash"></i> Delete</a></li>
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=search-car-serial"><i class="fa-solid fa-barcode"></i> Search by Serial</a></li>
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=search-car-model"><i class="fa-solid fa-car-side"></i> Search by Model</a></li>
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=search-car-year"><i class="fa-solid fa-calendar"></i> Search by Year</a></li>
                    </ul>
                </div>
            </div>

            <!-- Settings Dropdown -->
            <div class="col-lg-4 col-md-6">
                <div class="dropdown">
                    <button class="btn btn-info w-100 py-4 fs-3 fw-bold text-white shadow-lg rounded dropdown-toggle" data-bs-toggle="dropdown">
                        <i class="fa-solid fa-gear"></i> Settings
                    </button>
                    <ul class="dropdown-menu w-100">
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=sale-profile"><i class="fa-solid fa-user"></i> Profile</a></li>
                        <li><a class="dropdown-item fs-5" href="MainServlet?action=logout-sale"><i class="fa-solid fa-right-from-bracket"></i> Logout</a></li>
                    </ul>
                </div>
            </div>

        </div>

        <!-- Row 2: The Part - Create an Invoice -->
        <div class="row justify-content-center g-4 mt-3">
            
            <!-- The Part -->
            <div class="col-lg-5 col-md-6">
                <a href="MainServlet?action=the-part" class="btn btn-info w-100 py-4 fs-3 fw-bold text-white shadow-lg rounded">
                    <i class="fa-solid fa-gears"></i> The Part
                </a>
            </div>

            <!-- Create an Invoice -->
            <div class="col-lg-5 col-md-6">
                <a href="MainServlet?action=create-invoice" class="btn btn-info w-100 py-4 fs-3 fw-bold text-white shadow-lg rounded">
                    <i class="fa-solid fa-file-invoice"></i> Create an Invoice
                </a>
            </div>

        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>





