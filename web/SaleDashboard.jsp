<%-- 
    Document   : SaleDashboard.jsp
    Created on : 04-03-2025, 16:23:22
    Author     : Thanh Vinh
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- chuyển định dạng số -->

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
        <c:if test="${sessionScope.SALE != null}">
            <jsp:useBean id="SALE" scope="session" class="models.SalesPerson"/>
            <nav class="navbar navbar-expand-lg bg-info text-white p-3 shadow-lg">
                <div class="container-fluid d-flex justify-content-between align-items-center">
                    <h2 class="text-white fw-bold m-0">Welcome ${SALE.salesName}</h2>
                    <form class="d-flex">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-primary" type="submit"> Search </button>
                    </form>
                </div>
            </nav>



            <!-- Dashboard Content -->
            <div class="container py-4">

                <!-- Row 1: Customer - Car - Settings -->
                <div class="row justify-content-center g-4">

                    <!-- Customer Dropdown -->
                    <div class="col-lg-4 col-md-6 mb-3"> <!-- Add mb-3 here -->
                        <div class="dropdown">
                            <button class="btn btn-info w-100 py-4 fs-3 fw-bold text-white shadow-lg rounded dropdown-toggle" data-bs-toggle="dropdown">
                                <i class="fa-solid fa-users"></i> Customer
                            </button>
                            <ul class="dropdown-menu w-100">
                                <li><a class="dropdown-item fs-5" href="MainServlet?action=create-customer-page"><i class="fa-solid fa-plus"></i> Create </a></li>
                                <li><a class="dropdown-item fs-5" href="MainServlet?action=read-customer"><i class="fa-solid fa-eye"></i> Read </a></li>
                                <li><a class="dropdown-item fs-5" href="MainServlet?action=search-customer-page"><i class="fa-solid fa-search"></i> Search by Name </a></li>
                            </ul>
                        </div>
                    </div>

                    <!-- Car Dropdown -->
                    <div class="col-lg-4 col-md-6 mb-3"> <!-- Add mb-3 here -->
                        <div class="dropdown">
                            <button class="btn btn-info w-100 py-4 fs-3 fw-bold text-white shadow-lg rounded dropdown-toggle" data-bs-toggle="dropdown">
                                <i class="fa-solid fa-car"></i> Car
                            </button>
                            <ul class="dropdown-menu w-100">
                                <li><a class="dropdown-item fs-5" href="MainServlet?action=create-car-page"><i class="fa-solid fa-plus"></i> Create </a></li>
                                <li><a class="dropdown-item fs-5" href="MainServlet?action=read-car"><i class="fa-solid fa-eye"></i> Read </a></li>
                                <li><a class="dropdown-item fs-5" href="MainServlet?action=search-car"><i class="fa-solid fa-search"></i> Search </a></li>
                            </ul>
                        </div>
                    </div>

                    <!-- Settings Dropdown -->
                    <div class="col-lg-4 col-md-6 mb-3"> <!-- Add mb-3 here -->
                        <div class="dropdown">
                            <button class="btn btn-info w-100 py-4 fs-3 fw-bold text-white shadow-lg rounded dropdown-toggle" data-bs-toggle="dropdown">
                                <i class="fa-solid fa-gear"></i> Settings
                            </button>
                            <ul class="dropdown-menu w-100">
                                <li><a class="dropdown-item fs-5" href="MainServlet?action=sale-profile"><i class="fa-solid fa-user"></i> Profile </a></li>
                                <li><a class="dropdown-item fs-5" href="MainServlet?action=logout-sale"><i class="fa-solid fa-right-from-bracket"></i> Logout </a></li>
                            </ul>
                        </div>
                    </div>

                </div>

                <!-- Row 2: Statistics - The Part - Create an Invoice -->
                <div class="row justify-content-center g-4 mb-3"> <!-- Add mb-3 here -->

                    <!-- Statistics Dropdown -->
                    <div class="col-lg-4 col-md-6">
                        <div class="dropdown">
                            <button class="btn btn-info w-100 py-4 fs-3 fw-bold text-white shadow-lg rounded dropdown-toggle" data-bs-toggle="dropdown">
                                <i class="fa-solid fa-chart-line"></i> Statistics
                            </button>
                            <ul class="dropdown-menu w-100" style="min-width: 300px; max-height: 400px; overflow-y: auto;">
                                <li><a class="dropdown-item fs-5" href="MainServlet?action=statistics-cars"><i class="fa-solid fa-calendar"></i> Statistics of Cars Sold by Year</a></li>
                                <li><a class="dropdown-item fs-5" href="MainServlet?action=statistics-revenue"><i class="fa-solid fa-dollar-sign"></i> Statistics of Car Sales Revenue by Year</a></li>
                                <li><a class="dropdown-item fs-5" href="MainServlet?action=statistics-bestselling-models"><i class="fa-solid fa-trophy"></i> Statistics of Best-Selling Car Models</a></li>
                                <li><a class="dropdown-item fs-5" href="MainServlet?action=statistics-bestused-parts"><i class="fa-solid fa-cogs"></i> Statistics of Best Used Parts</a></li>
                                <li><a class="dropdown-item fs-5" href="MainServlet?action=statistics-top-mechanics"><i class="fa-solid fa-wrench"></i> Find the 3 Mechanics Assigned to the Most Repairs</a></li>
                            </ul>
                        </div>
                    </div>


                    <!-- The Part -->
                    <div class="col-lg-4 col-md-6 mb-3"> <!-- Add mb-3 here -->
                        <a href="MainServlet?action=the-part" class="btn btn-info w-100 py-4 fs-3 fw-bold text-white shadow-lg rounded">
                            <i class="fa-solid fa-gears"></i> The Part
                        </a>
                    </div>

                    <!-- Create an Invoice -->
                    <div class="col-lg-4 col-md-6 mb-3"> <!-- Add mb-3 here -->
                        <a href="MainServlet?action=create-invoice" class="btn btn-info w-100 py-4 fs-3 fw-bold text-white shadow-lg rounded">
                            <i class="fa-solid fa-file-invoice"></i> Create an Invoice
                        </a>
                    </div>

                </div>



                <br/>

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

                <!-- Customer List -->
                <c:if test="${not empty CUSTOMER_LIST}">
                    <div class="container mt-5">
                        <h2 class="text-center text-primary fw-bold">Customer List</h2>

                        <!-- Customer Table -->
                        <div class="table-responsive">
                            <table class="table table-hover table-bordered text-center align-middle">
                                <thead class="table-info">
                                    <tr>
                                        <th>Customer ID</th>
                                        <th>Name</th>
                                        <th>Phone</th>
                                        <th>Sex</th>
                                        <th>Address</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="customer" items="${CUSTOMER_LIST}">
                                        <tr>
                                            <td>${customer.custID}</td>
                                            <td>${customer.custName}</td>
                                            <td>${customer.phone}</td>
                                            <td>${customer.sex}</td>
                                            <td>${customer.cusAddress}</td>
                                            <td>
                                                <a href="MainServlet?action=edit-customer&id=${customer.custID}" class="btn btn-warning btn-sm">
                                                    <i class="fa-solid fa-pen"></i> Edit
                                                </a>
                                                <a href="MainServlet?action=delete-customer&id=${customer.custID}" 
                                                   class="btn btn-danger btn-sm" 
                                                   onclick="return confirm('Are you sure you want to delete this customer?');">
                                                    <i class="fa-solid fa-trash"></i> Delete
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>

                <!-- Car List -->
                <c:if test="${not empty CAR_LIST}">
                    <div class="container mt-5">
                        <h2 class="text-center text-primary fw-bold">Car List</h2>

                        <!-- Car Table -->
                        <div class="table-responsive">
                            <table class="table table-hover table-bordered text-center align-middle">
                                <thead class="table-info">
                                    <tr>
                                        <th>Car ID</th>
                                        <th>Serial Number</th>
                                        <th>Model</th>
                                        <th>Colour</th>
                                        <th>Year</th>
                                        <th>Price</th>  <!-- Cột giá -->
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="car" items="${CAR_LIST}">
                                        <tr>
                                            <td>${car.carID}</td>
                                            <td>${car.serialNumber}</td>
                                            <td>${car.model}</td>
                                            <td>${car.colour}</td>
                                            <td>${car.year}</td>
                                            <td><fmt:formatNumber value="${car.price}" pattern="#,###.0" /></td>  
                                            <td>
                                                <a href="MainServlet?action=edit-car&id=${car.carID}" class="btn btn-warning btn-sm">
                                                    <i class="fa-solid fa-pen"></i> Edit
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
                    </div>
                </c:if>

                <!-- các bảng thống kê -->
                <c:if test="${not empty carsSoldByYear}">
                    <div class="container mt-5">
                        <h2 class="text-center text-primary fw-bold">Statistics of Cars Sold by Year</h2>

                        <!-- Report Table for Cars Sold by Year -->
                        <div class="table-responsive">
                            <table class="table table-hover table-bordered text-center align-middle">
                                <thead class="table-info">
                                    <tr>
                                        <th>Year</th>
                                        <th>Cars Sold</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="report" items="${carsSoldByYear}">
                                        <tr>
                                            <td>${report.year}</td>
                                            <td>${report.carsSold}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>

                <c:if test="${not empty carSalesRevenueByYear}">
                    <div class="container mt-5">
                        <h2 class="text-center text-primary fw-bold">Statistics of Car Sales Revenue by Year</h2>

                        <!-- Report Table for Car Sales Revenue by Year -->
                        <div class="table-responsive">
                            <table class="table table-hover table-bordered text-center align-middle">
                                <thead class="table-info">
                                    <tr>
                                        <th>Year</th>
                                        <th>Revenue</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="report" items="${carSalesRevenueByYear}">
                                        <tr>
                                            <td>${report.year}</td>
                                            <td><fmt:formatNumber value="${report.revenue}" pattern="#,###.0" /></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>

                <c:if test="${not empty bestSellingCarModels}">
                    <div class="container mt-5">
                        <h2 class="text-center text-primary fw-bold">Statistics of Best-Selling Car Models</h2>

                        <!-- Report Table for Best-Selling Car Models -->
                        <div class="table-responsive">
                            <table class="table table-hover table-bordered text-center align-middle">
                                <thead class="table-info">
                                    <tr>
                                        <th>Model</th>
                                        <th>Sales Count</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="report" items="${bestSellingCarModels}">
                                        <tr>
                                            <td>${report.model}</td>
                                            <td>${report.salesCount}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>

                <c:if test="${not empty bestUsedParts}">
                    <div class="container mt-5">
                        <h2 class="text-center text-primary fw-bold">Statistics of Best Used Parts</h2>

                        <!-- Report Table for Best Used Parts -->
                        <div class="table-responsive">
                            <table class="table table-hover table-bordered text-center align-middle">
                                <thead class="table-info">
                                    <tr>
                                        <th>Part Name</th>
                                        <th>Total Used</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="report" items="${bestUsedParts}">
                                        <tr>
                                            <td>${report.partName}</td>
                                            <td>${report.totalUsed}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>

                <c:if test="${not empty top3Mechanics}">
                    <div class="container mt-5">
                        <h2 class="text-center text-primary fw-bold">Top 3 Mechanics with Most Repairs</h2>

                        <!-- Report Table for Top 3 Mechanics -->
                        <div class="table-responsive">
                            <table class="table table-hover table-bordered text-center align-middle">
                                <thead class="table-info">
                                    <tr>
                                        <th>Mechanic Name</th>
                                        <th>Repairs Handled</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="report" items="${top3Mechanics}">
                                        <tr>
                                            <td>${report.mechanicName}</td>
                                            <td>${report.repairsHandled}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>


            </div>
        </c:if>

        <c:if test="${sessionScope.SALE == null}">
            <div class="alert alert-danger" role="alert">
                You don't have any permission to access this resource. Please <a href="MainServlet?action=login-sale-page" class="alert-link fw-bold">login here</a>!
            </div>
        </c:if>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>






